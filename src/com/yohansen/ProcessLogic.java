package com.yohansen;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProcessLogic {
    public void process(String[] args){
        if(args[0].equalsIgnoreCase("-h")){
            annualScript();
        }else{
            convertion(args);
        }
    }

    private void commandNotFound(){
        System.out.println("Perintah tidak dikenali");
    }

    private void extNotFound(){
        System.out.println("Extension tidak dikenali");
    }

    private void annualScript(){
        System.out.println();
        System.out.println("Silahakan masukan perintah berikut : ");
        System.out.println("- Konversi File pada direktori yang sama = {directory}/{nama_file}.{ext_file}<spasi>-t<spasi>{ext_file}");
        System.out.println("  {ext_file} : text atau json");
        System.out.println("  Example = mytools /var/log/nginx/error.log -t json");
        System.out.println();
        System.out.println("- Konversi File serta memindahkan ke direktori berbeda = {directory}/{nama_file}.{ext_file}<spasi>-o<spasi>{{directory}/{nama_file}.{ext_file}");
        System.out.println("  Example = /var/log/nginx/error.log -o /User/johnmayer/Desktop/nginxlog.txt");
    }

    private void convertion(String[] command){
        if(command[1].equalsIgnoreCase("-t")){
            execT(command[0],command[2]);
        }else if(command[1].equalsIgnoreCase("-o")){
            convertFile(command[0],command[2]);
        }else{
            commandNotFound();
        }
    }

    private String getExt(String ext){
        var extension = "";
        if(ext.equalsIgnoreCase("text")){
            extension = ".txt";
        }else if(ext.equalsIgnoreCase("json")){
            extension = ".json";
        }
        return extension;
    }

    private void execT(String fileFrom, String ext){
        var fileExt = getExt(ext);
        if(fileExt.equalsIgnoreCase("")){
            extNotFound();
        }else{
            StringBuilder fileTo = new StringBuilder();
            var s = fileFrom.split("\\.");
            for(var i = 0; i < s.length-1;i++){
                fileTo.append(s[i]);
            }
            fileTo.append(fileExt);

            convertFile(fileFrom,fileTo.toString());
        }
    }

    private void convertFile(String fileFrom, String fileTo){
        try{
            System.out.println("Convertion File");
            Files.copy(Paths.get(fileFrom),Paths.get(fileTo));
        }catch (FileAlreadyExistsException ex) {
            System.out.println("File "+fileTo+" sudah tersedia");
        } catch (FileNotFoundException en) {
            System.out.println("File "+fileFrom+" tidak ditemukan");
        } catch (IOException ex) {
            System.out.println("Error File");
        }
    }
}

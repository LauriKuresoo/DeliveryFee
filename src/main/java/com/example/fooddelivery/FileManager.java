package com.example.fooddelivery;

import com.example.fooddelivery.entity.VechilePrices;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileManager{

    String fileLocation =  "\\fees\\vechiles.dat";

    public void writeToVechilesFile(String cityName, String vechileType, double price) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(new ClassPathResource(fileLocation).getFile(), true))){
            dos.writeUTF(cityName);
            dos.writeUTF(vechileType);
            dos.writeDouble(price);
        }
        catch ( FileNotFoundException e){
            throw new FileNotFoundException("The vechiles file was not found in the specified location: " + fileLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VechilePrices> readAllFromVechilesFile() throws IOException{
        List<VechilePrices> prices = new ArrayList<>();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(new ClassPathResource(fileLocation).getFile()))){
            while(dis.available()>0) {
                String cityName = dis.readUTF();
                String vechileType = dis.readUTF();
                double price = dis.readDouble();
                prices.add(new VechilePrices(cityName, vechileType, price));
            }
            return prices;
        }
        catch ( FileNotFoundException e){
            throw new FileNotFoundException("The vechiles file was not found in the specified location: " + fileLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

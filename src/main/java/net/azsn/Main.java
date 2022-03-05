package net.azsn;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

public class Main {



    public static void main(String... args){

        Domain domain = new Domain(3);


        while(domain.hasNext()){
            printValidURL(domain.next());
        }


    }

    private static class Domain {
        final String[] characters = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z", "0",
                "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        final private int[] indexes;
        final private int length;
        public Domain(int length){
            indexes = new int[length];
            this.length = length;
        }

        public String next(){
            StringBuilder domain = new StringBuilder();

            domain.append("http://");

            for(int index : indexes){
                domain.append(characters[index]);
            }

            incrementIndexes();

            domain.append(".hu");
            return domain.toString();
        }

        private void incrementIndexes(){
            for(int i = length - 1; i >= 0; i--){

                indexes[i]++;

                if(indexes[i] > characters.length - 1){
                    indexes[i] = 0;
                    continue;
                }

                break;

            }
        }

        public boolean hasNext() {
            for(int index : indexes){
                if(index < length - 1) return true;
            }
            return false;
        }
    }


    private static void printValidURL(String url){
        try {
            URI uri = new URI(url);
            InputStream inputStream = uri.toURL().openStream();
            System.out.println(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println(url + " " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println(url + " " + e.getMessage());
        }
    }
}

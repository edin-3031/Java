package com.EMailGenarator;


import java.util.Scanner;

import static com.EMailGenarator.Mail.print;

class Mail{
    private String Ime;
    private String Prezime;
    private String Odjel;
    private String Sifra;
    private String Mail;

    static <T> void print(T txt){
        System.out.print(txt);
    }

    Scanner input=new Scanner(System.in);

    public Mail(String ime, String prezime){
        Ime=ime;
        Prezime=prezime;
    }

    //Odabir Odjela
    public void Meni(){

        String[] odjeli=new String[]{"odjel1","odjel2","odjel3"};
        int odabir;
        boolean ispravno=true;
        do{
            print("Korisnik: "+Ime+" "+Prezime+"\nOdaberite odjel:\n1 - odjel1\n2 - odjel2\n3 - odjel3\n\nVaš izbor: ");
            odabir=input.nextInt();
            if(odabir<=0 || odabir>=4)
            {
                ispravno=false;
                print("Odabrali ste nevažeću opciju\n");
            }
            else{
                switch (odabir){
                    case 1:Odjel=odjeli[odabir-1];ispravno=true;break;
                    case 2:Odjel=odjeli[odabir-1];ispravno=true;break;
                    case 3:Odjel=odjeli[odabir-1];ispravno=true;break;
                    default:print("Odabrali ste nevažeću opciju\n");break;
                }
            }
        }while (!ispravno);
    }

    //Generisanje mail-a
    public void GeneratingMail(){
        String ime=Ime.toLowerCase();
        String prezime=Prezime.toLowerCase();
        String odjel=Odjel.toLowerCase();

        Mail=ime+"."+prezime+"@"+Odjel+".CompanyX.com";
    }

    //Generisanje Šifre
    public void GeneratingPassword(int length){
        String PassSet="ABCDEFGHIJKLMNOPQRSTUVWXY1234567890#%&*?";
        char[]pass=new char[length];
        for(int i=0; i<length; i++){
            int rand=(int)(Math.random()*PassSet.length());
            pass[i]=PassSet.charAt(rand);
        }

        Sifra=String.valueOf(pass);
    }

    //PrikazPodataka
    public void PrikazPodataka(){
        print("Vaši dodjeljeni podaci su:\nIme i prezime: "+Ime+" "+Prezime+"\n"+"Vaš odjel je: "+Odjel+"\nVaš dodjeljen E-Mail je: "+Mail+"\n Vaša dodjeljena šifra je: "+Sifra+"\n");
    }
}

public class MailGenerator {

    public static void main(String[] args) {

        Mail mail=new Mail("Edin","Pinjić");

        mail.Meni();

        mail.GeneratingMail();

        mail.GeneratingPassword(12);

        mail.PrikazPodataka();
    }
}

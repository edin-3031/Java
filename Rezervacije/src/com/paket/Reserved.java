package com.paket;
import java.util.Scanner;

public class Reserved {

    public static int prethodniRed;
    public static int prethodnaKolona;

    public static class Avion{
        int row,column,sredina;
        char[][] matrica;

        public void KreiranjeMatrice(){
            print("Unesite broj redova matrice: ");
            row=input.nextInt();
            print("Unesite broj kolona matrice: ");
            column=input.nextInt();
            sredina=column/2;
            matrica=new char[row][column];
            for(int i=0; i<row; i++){
                for (int j=0; j<column; j++) {
                    matrica[i][j] = '-';
                    if (j == sredina)
                        matrica[i][j] ='|';
                }
            }
        }

        public void Ispismatrice(){
            for(int i=0; i<row; i++){
                for (int j=0; j<column; j++) {
                    print(matrica[i][j]);
                }
                print("\n");
            }
        }

        public boolean ProvjeraKoordinata(int red, int kolona, boolean otkazivanje){
            red--;
            kolona--;
            if(red<0 || kolona<0)
            {
                print("\nKoordinata ne može biti 0 ili negativni broj!\n");
                return false;
            }
            else if((kolona)==sredina){
                print("\nNe možemo Vam odobriti sjedjenje na podu!\n");
                return false;
            }
            else if(red>(row-1) || kolona>(column-1)){
                print("\nNemamo toliko sjedišta!\n");
                return false;
            }

            if(!otkazivanje) {
                if (matrica[red][kolona] == '+') {
                    print("\nOdabrano sjedište je već zauzeto!\n");
                    return false;
                }
            }
            else if(otkazivanje) {
                if (matrica[red][kolona] == '-') {
                    print("\nOdabrano sjedište nije zauzeto!\n");
                    return false;
                }
            }
            return true;
        }

        public void Rezervacija(){
            print("\n");
            Ispismatrice();
            print("\nUnesite koordinate sjedišta koje želite rezervisati: \nRed: ");
            int red=input.nextInt();
            print("\nKolona: ");
            int kolona=input.nextInt();

            if(ProvjeraKoordinata(red,kolona,false)){
                matrica[red-1][kolona-1]='+';
                print("\nUspješno ste rezervisali mjesto!\n");
                prethodniRed=red;
                prethodnaKolona=kolona;
            }
            else
                print("\nNiste uspješno rezervisali mjesto! Molimo da ponovite\n");

        }

        public void OtkazivanjeRezervaije(){
            char odabir;

            print("Da li možda želite otkazati posljednju spremljenu koordinatu? Y/N: ");
            odabir=input.next().charAt(0);


            if (odabir == 'Y' || odabir == 'y') {
                if(ProvjeraKoordinata(prethodniRed,prethodnaKolona,true)) {
                    matrica[prethodniRed - 1][prethodnaKolona - 1] = '-';
                    print("\nUspješno ste otkazali posljednje spremljenu rezervaciju!\n");
                }
            }
            if (odabir == 'N' || odabir == 'n') {
                int noviRed, novaKolona;
                Ispismatrice();
                print("\nUnesite koordinate sjedišta koje želite rezervisati: \nRed: \n");
                noviRed = input.nextInt();
                print("\nKolona: ");
                novaKolona = input.nextInt();
                if (ProvjeraKoordinata(noviRed, novaKolona, true)) {
                    matrica[noviRed - 1][novaKolona - 1] = '-';
                    print("\nUspješno ste otkazali rezervaciju!\n");
                } else
                    print("\nNiste uspješno otkazali rezervisano mjesto! Molimo da ponovite\n");
            }
            else
                print("\nUnijeli ste nevažeću opciju.\n");

        }

        public void Meni(){
            int odabir;

            do{
                print("\nOdaberite opciju:\n\n1 - Prikaz sjedišta\n2 - Rezervacija mjesta\n3 - Ukidanje rezervacije\n4 - Izlaz");
                print("\n\nVaš odabir: ");
                odabir=input.nextInt();

                switch (odabir){
                    case 1:Ispismatrice();break;
                    case 2:Rezervacija();break;
                    case 3:OtkazivanjeRezervaije();break;
                    case 4:break;
                    default:print("\nUnijeli ste nevažeću opciju!\n");
                }

            }while (odabir!=4);

            print("\n-------Hvala na korištenju-------\n");
        }
    }

    static <T> void print(T txt){
        System.out.print(txt);
    }

    public static Scanner input=new Scanner(System.in);

    public static void main(String[] args) {
        Avion avion=new Avion();
        avion.KreiranjeMatrice();

        avion.Meni();
    }
}
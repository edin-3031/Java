import javax.print.attribute.standard.MediaSize;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class Atm {
    static Scanner input=new Scanner(System.in);

    static <T> void print(T txt){
        System.out.print(txt);
    }

    static Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    static String ZadnjiLog;

    public static boolean ProvjeraPINa(String stariPIN, String noviPin){

        if(noviPin.length()!=4)
        {
            print("\nNiste unijeli ispravani PIN\n");
            return false;
        }
        else{
            for(int i=0; i<stariPIN.length(); i++){
                if(noviPin.charAt(i)!=stariPIN.charAt(i))
                {
                    print("\nNiste unijeli ispravani PIN\n");
                    return false;
                }
            }
        }
        return true;
    }

    public static void UnosPINa(String pin){

        boolean ispravno=false;

        do{
            print("Unesite Vaš PIN: ");
            String PIN=input.next();

            if(ProvjeraPINa(pin,PIN)){
                ispravno=true;
            }

        }while (!ispravno);
    }

    static class Korisnik{
        public String Ime;
        public String PIN;
        public double Stanje;

        public Korisnik(String ime, String pin, double stanje){
            Ime=ime;
            PIN=pin;
            Stanje=stanje;
        }
    }

    public static void Meni(Korisnik k){
        int odabir;
        boolean ispravanOdabir=true;

        do {
            UnosPINa(k.PIN);
            do {
                print("\nDobro došli "+k.Ime+", odaberite opciju:\n");
                print("1 - Uvid u stanje\n");
                print("2 - Preuzimanje novca\n");
                print("3 - Dodavanje novca\n");
                print("4 - Promjena PIN-a\n");
                print("5 - Zadnji log\n");
                print("6 - Odjava\n");

                print("\nVaš odabir: ");
                odabir=input.nextInt();

                if(odabir <= 0 || odabir >= 7)
                {
                    ispravanOdabir=false;
                    print("\nOdabrali ste nepostojeću opciju. Pokušajte ponovo.\n");
                }

                switch (odabir){
                    case 1:UvidStanja(k);break;
                    case 2:PreuzimanjeNovca(k);break;
                    case 3:DodavanjeNovca(k);break;
                    case 4:PromjenaPina(k);break;
                    case 5:print("\n"+ZadnjiLog+"\n");break;
                    case 6:print("\n------------------Uspješno ste se odjavili. Hvala na korištenju.------------------\n");break;
                }

            } while (odabir != 6);
        }while (odabir==6);

    }

    public static void PromjenaPina(Korisnik k) {
        boolean ispravan=false;
        String noviPin;
        do {
            print("Unesite Vaš trenutni PIN: ");
            String trenutniPIN = input.next();
            ispravan = ProvjeraPINa(k.PIN, trenutniPIN);
        }while (!ispravan);
        do{
            print("\nUnesite Vaš novi PIN: ");
            noviPin=input.next();
            if(noviPin.length()!=4)
            {
                ispravan=false;
                print("\nNiste unijeli ispravan PIN, mora imati 4 karaktera. Probajte ponovo.\n");
            }
            else
                ispravan=true;
        }while (!ispravan);

        k.PIN=noviPin;
        print("\nUspješno ste izmijenili svoj PIN\n");


        ZadnjiLog="Vaš zadnji log je bio izmjena PIN-a Vašeg računa na datum "+date;
    }

    public static void DodavanjeNovca(Korisnik k) {
        boolean ispravno=true;
        double iznos=0;

        do {
            if(!ispravno){
                print("\nNije moguće rukovati sa negativnim brojevima.\n");
            }

            print("Unesite iznos: ");
            try {
                iznos = input.nextDouble();
            }catch (Exception e){
                print("\nMolimo da unosite brojeve!\n");
                break;
            }
            if (iznos < 0) {
                ispravno = false;
            } else{
                ispravno = true;

                k.Stanje+=iznos;
                print("\nUspješno ste izvršili uplatu!\n\n");
            }
        }while (!ispravno);



        ZadnjiLog="Vaš zadnji log je bio dodavanje novca na Vaš račun od iznosu od "+iznos+" KM na datum "+date;
    }

    public static void PreuzimanjeNovca(Korisnik k) {
        boolean ispravno=true;
        double iznos=0;

        do {
            print("Unesite iznos: ");
            try {
                iznos = input.nextDouble();
            }catch (Exception e){
                print("\nMolimo da unosite brojeve!\n");
                break;
            }
            if (iznos > k.Stanje) {
                ispravno = false;
                print("\nUnijeli ste iznos koji je veći od Vašeg stanja! Molimo da ponovite.\n");
            }
            else if(iznos<=0){
                ispravno=false;
                print("\nNije dozvoljen unos negativnog iznosa! Molimo da ponovite.\n");
            }
            else{
                ispravno = true;

                k.Stanje-=iznos;
                print("\nUspješno ste izvršili isplatu!\n\n");
            }
        }while (!ispravno);

        ZadnjiLog="Vaš zadnji log je bio preuzimanje novca sa Vašeg računa od iznosu od "+iznos+" KM na datum "+date;
    }

    public static void UvidStanja(Korisnik k) {
        print("\nVaše trenutno stanje računa je: "+k.Stanje+" KM\n");

        ZadnjiLog="Vaš zadnji log je bio uvid stanja Vašeg računa na datum "+date;
    }


    public static void main(String[] args) {
        Korisnik k=new Korisnik("Edin","1234",10020);

        Meni(k);
    }
}

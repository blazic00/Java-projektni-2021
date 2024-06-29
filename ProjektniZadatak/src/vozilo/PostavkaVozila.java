package vozilo;

import java.util.Random;


public class PostavkaVozila {
    protected static void postavljanjeVozilaNaPocetakPuta(Vozilo vozilo)
    {
        Random generator=new Random();
        if(vozilo.getPocetniX()==14&&vozilo.getPocetniY()==29)
        {
            if(generator.nextBoolean()==true)
            {
                vozilo.setPocetniX(13);
                vozilo.setPocetniY(0);
            }
        }
        else if(vozilo.getPocetniX()==8&&vozilo.getPocetniY()==29)
        {
            if(generator.nextBoolean()==true)
            {
                vozilo.setPocetniX(0);
                vozilo.setPocetniY(21);
            }
        }
        else if(vozilo.getPocetniX()==22&&vozilo.getPocetniY()==29)
        {
            if(generator.nextBoolean()==true)
            {
                vozilo.setPocetniX(29);
                vozilo.setPocetniY(20);
            }
        }

    }
    protected static void odredjivanjePuta(Vozilo vozilo)
    {
        int[] x=new int[2];
        int[] y=new int[3];
        if(vozilo.getPocetniX()==14&&vozilo.getPocetniY()==29)
        {
             x=new int[30];
             y=new int [30];
            for(int i=0;i<30;i++)
            {
                x[i]=14;
                y[i]=29-i;
            }
        }
        else if(vozilo.getPocetniX()==13&&vozilo.getPocetniY()==0)
        {
            x=new int[30];
            y=new int [30];
            for(int i=0;i<30;i++)
            {
                x[i]=13;
                y[i]=i;
            }
        }
        else if(vozilo.getPocetniX()==8&&vozilo.getPocetniY()==29)
        {
            x=new int[18];
            y=new int [18];
            for(int i=0;i<18;i++)
            {
                if(i<10)
                {
                    x[i]=8;
                    y[i]=29-i;
                }
                else
                {
                    x[i]=8+9-i;
                    y[i]=20;
                }

            }
        }
        else if(vozilo.getPocetniX()==0&&vozilo.getPocetniY()==21)
        {
            x=new int[16];
            y=new int [16];
            for(int i=0;i<16;i++)
            {
                if(i<8)
                {
                    x[i]=i;
                    y[i]=21;
                }
                else
                {
                    x[i]=7;
                    y[i]=21-7+i;
                }

            }
        }
        else if(vozilo.getPocetniX()==22&&vozilo.getPocetniY()==29)
        {
            x=new int[16];
            y=new int [16];
            for(int i=0;i<16;i++)
            {
                if(i<9)
                {
                    x[i]=22;
                    y[i]=29-i;
                }
                else
                {
                    x[i]=22-8+i;
                    y[i]=21;
                }

            }
        }
        else if (vozilo.getPocetniX()==29&&vozilo.getPocetniY()==20)
        {
            x=new int[18];
            y=new int [18];
            for(int i=0;i<18;i++)
            {
                if(i<9)
                {
                    x[i]=29-i;
                    y[i]=20;
                }
                else
                {
                    x[i]=21;
                    y[i]=20-8+i;
                }

            }
        }
        vozilo.setPutanjaX(x);
        vozilo.setPutanjaY(y);
    }
}

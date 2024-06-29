package mapa;


import zeljeznickaKompozicija.Voz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Stanica  extends ObjekatMape {

    private String oznaka;
    private LinkedList<Voz> vozoviUStanici=new LinkedList<>();
    private LinkedList<Voz> dolazniVozovi=new LinkedList<>();
    private static  Mapa mapa;


    private static LinkedList<Voz> listaVozovaAB=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaBA=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaBC=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaCB=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaCE=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaCD=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaDC=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaEC=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaXA=new LinkedList<>();
    private static LinkedList<Voz> listaVozovaXE=new LinkedList<>();



    public Stanica(Mapa mapa)
    {
        this.mapa=mapa;
    }

    public static void pomoc()
    {
        System.out.println("vozoviAB: "+listaVozovaAB.size());
        System.out.println("vozoviBA: "+listaVozovaBA.size());
        System.out.println("vozoviCB: "+listaVozovaCB.size());
        System.out.println("vozoviCE: "+listaVozovaCE.size());
        System.out.println("vozoviCD: "+listaVozovaCD.size());
        System.out.println("vozoviDC: "+listaVozovaDC.size());
        System.out.println("vozoviEC: "+listaVozovaEC.size());
        System.out.println("vozoviBC: "+listaVozovaBC.size());


    }

  private static boolean daLiSuVozoviIzasliIzStanice(LinkedList<Voz> listaVozova)
  {
      for(int i=0;i<listaVozova.size();i++)
      {
            synchronized (listaVozova.get(i)) {
                if (listaVozova.get(i).isDaLijeCijelaKompozicijaIzaslaIzStanice() == false)
                    return false;
            }
      }
      return true;
  }

  private static float minimalnaBrzina(LinkedList<Voz> listaVozova)
  {
      float min= -1f;
      for(int i=0;i<listaVozova.size();i++)
      {
         float pom= listaVozova.get(i).getBrzina();
         if(pom>min)
             min=pom;
      }
      return min;
  }

  public static boolean daLiVozMozeKrenuti(Voz voz)
  {
      if(voz.getPolaznaStanica()==null && voz.getPocetakY()==29 && voz.getPocetakX()==2){
          if(listaVozovaXA.size()!=0) {
              return false;
          }
          else {
              listaVozovaXA.add(voz);
              return true;
          }
      }
      if(voz.getPolaznaStanica()==null&& voz.getPocetakY()==25 && voz.getPocetakX()==29){
          if(listaVozovaXE.size()!=0) {
              return false;
          }
          else {
              listaVozovaXE.add(voz);
              return true;
          }

      }
      if(voz.getPolaznaStanica().getOznaka().equals("A")) {
          if(listaVozovaBA.size()!=0)
                return false;
          else {
              if(Stanica.daLiSuVozoviIzasliIzStanice(listaVozovaAB)==false)
                  return false;
               else
              {
                  listaVozovaAB.add(voz);
                  voz.setBrzina(Stanica.minimalnaBrzina(listaVozovaAB));
                  return true;
              }

          }
      }
      if(voz.getPolaznaStanica().getOznaka().equals("E")) {
          if(listaVozovaCE.size()!=0)
              return false;
          else {
              if(Stanica.daLiSuVozoviIzasliIzStanice(listaVozovaEC)==false)
                  return false;
              else
              {
                  listaVozovaEC.add(voz);
                  voz.setBrzina(Stanica.minimalnaBrzina(listaVozovaEC));
                  return true;
              }
          }
      }
      if(voz.getPolaznaStanica().getOznaka().equals("D")) {
        if(listaVozovaCD.size()!=0)
            return false;
        else {
            if(Stanica.daLiSuVozoviIzasliIzStanice(listaVozovaDC)==false)
                return false;
            else
            {
                listaVozovaDC.add(voz);
                voz.setBrzina(Stanica.minimalnaBrzina(listaVozovaDC));
                return true;
            }
        }
      }
      if(voz.getPolaznaStanica().getOznaka().equals("B")) {
          if (voz.getSljedecaStanica().getOznaka() .equals("A")) {
              if(listaVozovaAB.size()!=0)
                  return false;
              else {
                  if(Stanica.daLiSuVozoviIzasliIzStanice(listaVozovaBA)==false)
                      return false;
                  else
                  {
                      listaVozovaBA.add(voz);
                      voz.setBrzina(Stanica.minimalnaBrzina(listaVozovaBA));
                      return true;
                  }
              }
          }
          else{
              if(listaVozovaCB.size()!=0)
                  return false;
              else {
                  if(Stanica.daLiSuVozoviIzasliIzStanice(listaVozovaBC)==false)
                      return false;
                  else
                  {
                      listaVozovaBC.add(voz);
                      voz.setBrzina(Stanica.minimalnaBrzina(listaVozovaBC));
                      return true;
                  }
              }
          }
      }
      if(voz.getPolaznaStanica().getOznaka().equals("C")) {
          if (voz.getSljedecaStanica().getOznaka().equals("B")) {
              if(listaVozovaBC.size()!=0)
                  return false;
              else {
                  if(Stanica.daLiSuVozoviIzasliIzStanice(listaVozovaCB)==false)
                      return false;
                  else
                  {
                      listaVozovaCB.add(voz);
                      voz.setBrzina(Stanica.minimalnaBrzina(listaVozovaCB));
                      return true;
                  }
              }
          }
          else if (voz.getSljedecaStanica().getOznaka() .equals("E")) {
              if(listaVozovaEC.size()!=0)
                  return false;
              else {
                  if(Stanica.daLiSuVozoviIzasliIzStanice(listaVozovaCE)==false)
                      return false;
                  else
                  {
                      listaVozovaCE.add(voz);
                      voz.setBrzina(Stanica.minimalnaBrzina(listaVozovaCE));
                      return true;
                  }
              }
          }
          else {
              if(listaVozovaDC.size()!=0)
                  return false;
              else {
                  if(Stanica.daLiSuVozoviIzasliIzStanice(listaVozovaCD)==false)
                      return false;
                  else
                  {
                      listaVozovaCD.add(voz);
                      voz.setBrzina(Stanica.minimalnaBrzina(listaVozovaCD));
                      return true;
                  }
              }
          }
      }
      return false;
  }

   public static void stanjeNaPrugama(Stanica trenutna,Voz voz)
   {
           if(voz.getPolaznaStanica()==null && trenutna.getOznaka().equals("A")) {
               listaVozovaXA.remove(voz);
               return;
           }
           if(voz.getPolaznaStanica()==null && trenutna.getOznaka().equals("E")) {
               listaVozovaXE.remove(voz);
               return;
           }

           if (voz.getPolaznaStanica().getOznaka().equals("A")) {
               listaVozovaAB.remove(voz);
               return;
           }
           if (voz.getPolaznaStanica().getOznaka().equals("E")) {
               listaVozovaEC.remove(voz);
               return;
           }
           if (voz.getPolaznaStanica().getOznaka().equals("D")) {
               listaVozovaDC.remove(voz);
               return;
           }
           if (voz.getPolaznaStanica().getOznaka().equals("B")) {
               if (trenutna.getOznaka().equals("A")) {
                   listaVozovaBA.remove(voz);
                   return;
               } else {
                   listaVozovaBC.remove(voz);
                   return;
               }
           }
           if (voz.getPolaznaStanica().getOznaka().equals("C")) {
               if (trenutna.getOznaka().equals("B")) {
                   listaVozovaCB.remove(voz);
                   return;
               } else if (trenutna.getOznaka().equals("E")) {
                   listaVozovaCE.remove(voz);
                   return;
               } else {
                   listaVozovaCD.remove(voz);
                   return;
               }
           }

   }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public LinkedList<Voz> getVozoviUStanici() {
        return vozoviUStanici;
    }

    public void pocetneKordinateVoza(Voz voz)
    {
        if(this.getOznaka().equals("A")) {
            voz.setPocetakX(2);
            voz.setPocetakY(27);
            voz.setSljedecaStanica((Stanica)this.mapa.getObjekatSaMape(6,6));
        }
        else if(this.getOznaka().equals("D")){
            voz.setPocetakX(26);
            voz.setPocetakY(1);
            voz.setSljedecaStanica((Stanica)this.mapa.getObjekatSaMape(12,20));
        }
        else if(this.getOznaka().equals("E")){
            voz.setPocetakX(26);
            voz.setPocetakY(25);
            voz.setSljedecaStanica((Stanica)this.mapa.getObjekatSaMape(13,20));
        }
        else if(this.getOznaka().equals("B")){
            if(voz.getOdredisnaStanica().getOznaka().equals("A"))
            {
                voz.setPocetakX(6);
                voz.setPocetakY(6);
                voz.setSljedecaStanica((Stanica)this.mapa.getObjekatSaMape(27,2));
            }
            else
            {
                voz.setPocetakX(7);
                voz.setPocetakY(6);
                voz.setSljedecaStanica((Stanica)this.mapa.getObjekatSaMape(12,19));
            }
        }
        else if(this.getOznaka().equals("C"))
        {
            if(voz.getOdredisnaStanica().getOznaka().equals("E"))
            {
                voz.setPocetakX(20);
                voz.setPocetakY(13);
                voz.setSljedecaStanica((Stanica)this.mapa.getObjekatSaMape(25,26));
            }
         else  if(voz.getOdredisnaStanica().getOznaka().equals("D"))
            {
                voz.setPocetakX(20);
                voz.setPocetakY(12);
                voz.setSljedecaStanica((Stanica)this.mapa.getObjekatSaMape(1,26));
            }
            else
            {
                voz.setPocetakX(19);
                voz.setPocetakY(12);
                voz.setSljedecaStanica((Stanica)this.mapa.getObjekatSaMape(6,7));
            }

        }
    }
    public static boolean daLiAutoMozePreciPrekoPrelaza(int pocetniY,int pocetniX)
    {
        if((pocetniY==29&&pocetniX==14)||(pocetniY==0&&pocetniX==13))
        {
            for(int i=0;i<listaVozovaBC.size();i++)
            {
                Voz voz=listaVozovaBC.get(i);
                synchronized (voz) {
                    if (voz.isDaLiJeVozProsaoPrekoPrelaza() == false)
                        return false;
                }
            }
            for(int i=0;i<listaVozovaCB.size();i++)
            {
                Voz voz=listaVozovaCB.get(i);
                synchronized (voz) {
                    if (voz.isDaLiJeVozProsaoPrekoPrelaza() == false)
                        return false;
                }
            }

        }
    else if((pocetniY==29&&pocetniX==8)||(pocetniY==21&&pocetniX==0))
        {
            for(int i=0;i<listaVozovaAB.size();i++)
            {
                Voz voz=listaVozovaAB.get(i);
                synchronized (voz) {
                    if (voz.isDaLiJeVozProsaoPrekoPrelaza() == false)
                        return false;
                }
            }
            for(int i=0;i<listaVozovaBA.size();i++)
            {
                Voz voz=listaVozovaBA.get(i);
                synchronized (voz) {
                    if (voz.isDaLiJeVozProsaoPrekoPrelaza() == false)
                        return false;
                }
            }

        }
        else if((pocetniY==29&&pocetniX==22)||(pocetniY==20&&pocetniX==29))
        {
            for(int i=0;i<listaVozovaCE.size();i++)
            {
                Voz voz=listaVozovaCE.get(i);
                synchronized (voz) {
                    if (voz.isDaLiJeVozProsaoPrekoPrelaza() == false)
                        return false;
                }
            }
            for(int i=0;i<listaVozovaEC.size();i++)
            {
                Voz voz=listaVozovaEC.get(i);
                synchronized (voz) {
                    if (voz.isDaLiJeVozProsaoPrekoPrelaza() == false)
                        return false;
                }
            }

        }
        return true;
    }

}

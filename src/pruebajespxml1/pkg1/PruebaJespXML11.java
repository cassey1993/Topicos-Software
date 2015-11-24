/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajespxml1.pkg1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.jespxml.JespXML;
import org.jespxml.excepciones.TagHijoNotFoundException;
import org.jespxml.modelo.Atributo;
import org.jespxml.modelo.Comentario;
import org.jespxml.modelo.Encoding;
import org.jespxml.modelo.Tag;
import org.xml.sax.SAXException;


/**
 *
 * @author pperezp
 */
public class PruebaJespXML11 implements Runnable{

    /**
     * @param args the command line arguments
     */
private int numero,bucle;
 private Thread hilo;
  private String nombreHilo;

  
public PruebaJespXML11( int d,String nombre ) {
        this.numero = d;
        this.bucle = 0;
        nombreHilo = nombre;
    System.out.println("Creando " + nombreHilo);
        }

 public void run() {
    System.out.println("Ejecutando " + nombreHilo );
    
        Random rnd=new Random();
        for (int bucle=0;bucle<numero;bucle++)
        { 
        long x=(long)(rnd.nextDouble()*103);
        long pos = (int) Math.floor(Math.random() * 100)+x;
        buscar2(pos, numero);
        }
    

    System.out.println("Hilo " + nombreHilo + " termino.");
  }
 
 

 public static void buscar2(long vector,int tamaño){
     
     JespXML xml2 = new JespXML(new File("parties.xml"));
        try {
            
            Tag segundaraiz = xml2.leerXML();
           String empresas []=new String [4];
           int sab=0;
            long cont=0;
            
            int P=0;
                for(Tag libro2 : segundaraiz.getTagsHijos()){
                
                if (cont==vector){
                Tag titulo, autor;
                titulo = libro2.getTagHijoByName("Codigo");
                autor = libro2.getTagHijoByName("Nombre");
               
                if(sab>0){
                sab++;
                }
                empresas[sab]=titulo.getContenido();
                sab++;
                empresas[sab]=autor.getContenido();
                if(sab==3){
               sab=0;
               crear(empresas);
                 cont++;
                }
                }else{
                    cont++;
                }
                
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagHijoNotFoundException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
public static String[] buscar(){
            
            Random rnd=new Random();
            
                int cuentas = (int)(rnd.nextDouble()*9);;
                cuentas++;
                int vector2 []=new int [cuentas];
                String entero []=new String[cuentas*2];
            JespXML xml = new JespXML(new File("cuentas.xml"));
            try {
            Tag raiz = xml.leerXML();
                for (int i=0;i<cuentas;i++)
                { 
                int x=(int)(rnd.nextDouble()*100);
                vector2[i]=x;
                }
                int cont=0;
                int P=0;
                int T=0;
                for(Tag libro : raiz.getTagsHijos()){
                    
                if(P<cuentas){
                if (cont==vector2[P]){
                //System.out.println(libro);
                Tag titulo, autor;
                
                titulo = libro.getTagHijoByName("Codigo");
                autor = libro.getTagHijoByName("Nombre");
               
                entero[T]=titulo.getContenido();
                T++;
                entero[T]=autor.getContenido();
                T++;

                P++;
                cont=0;
                
                }else{
                    cont++;
                }
                }
            }
                } catch (ParserConfigurationException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagHijoNotFoundException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        }
            return entero;
}
public static void crear(String[] n){
    
            Tag comprobante = new Tag("ns1:Comprobante");
            comprobante.addAtributo(new Atributo("xmlns:ns1", "http://creativosdigitales.co/schema/docusfera.xsd"));
            Tag Tipo = new Tag("Tipo");
            Tipo.addContenido("Egresos");
            Tag Numero = new Tag("Numero");
             Random rnd=new Random();
             String numero = String.valueOf((int)(rnd.nextDouble()*10));
            for (int i=0;i<4;i++)
                { 
                int x=(int)(rnd.nextDouble()*10);
                numero =  numero + String.valueOf(x);
                }
            Numero.addContenido(numero);
            Tag Fecha = new Tag("Fecha");
            int x=(int)(rnd.nextDouble()*30)+1;
            String fecha = String.valueOf(x);
            if(fecha.length() == 1){
                fecha = "0" + fecha;
            }
            x=(int)(rnd.nextDouble()*12)+1;
            String meses = String.valueOf(x);
            if(meses.length() == 1){
                meses = "0" + meses;
            }
            fecha =  "2015-" + meses +"-"+ fecha;
            Fecha.addContenido(fecha); 
            Tag Nota = new Tag("Nota");
            Nota.addContenido("pago"); 
            Tag Empresa = new Tag("Empresa");
            Tag Codigo = new Tag("Codigo");
            Codigo.addContenido(String.valueOf(n[0]));
            Tag Nombre = new Tag("Nombre");
            Nombre.addContenido(String.valueOf(n[1]));
            Tag Tercero = new Tag("Tercero");
            Tag Codigoter = new Tag("Codigo");
            Codigoter.addContenido(String.valueOf(n[2]));
            Tag Nombreter = new Tag("Nombre");
            Nombreter.addContenido(String.valueOf(n[3]));
            Tag Totales = new Tag("Totales");
            Tag Valor = new Tag("Valor");
            int valorguardar=(int)(rnd.nextDouble()*10000000);
            Valor.addContenido(String.valueOf(valorguardar));
            x=(int)(rnd.nextDouble()*10)+1;
            String[] test;
            Tag Contabilizacion = new Tag("Contabilizacion");

            comprobante.addTagHijo(Tipo);
            comprobante.addTagHijo(Numero);
            comprobante.addTagHijo(Fecha);
            comprobante.addTagHijo(Nota);
            comprobante.addTagHijo(Empresa);
            Empresa.addTagHijo(Codigo);
            Empresa.addTagHijo(Nombre);
            comprobante.addTagHijo(Tercero);
            Tercero.addTagHijo(Codigoter);
            Tercero.addTagHijo(Nombreter);
            comprobante.addTagHijo(Totales);
            Totales.addTagHijo(Valor);
            comprobante.addTagHijo(Contabilizacion);
            test = buscar();
            int lleno=0;

            for (int i = 0 ; i < test.length ; i++) {
            Tag Asiento = new Tag("ns1:Asiento");
            Tag Libro = new Tag("Libro");
            Libro.addContenido("COMUN");
            Tag Debitos = new Tag("Debitos");
            Tag Creditos = new Tag("Creditos");
            Tag Debitosn = new Tag("Debitos");
            Tag Creditosn = new Tag("Creditos");
            
            
                Contabilizacion.addTagHijo(Asiento);
                Asiento.addTagHijo(Libro);
                Tag Cuenta = new Tag("Cuenta");
                Tag CodigoCuenta = new Tag("Codigo");
                Tag NombreCuenta = new Tag("Nombre");
                Asiento.addTagHijo(Cuenta);
                CodigoCuenta.addContenido(test[i]);
                Cuenta.addTagHijo(CodigoCuenta);
                i++;
                NombreCuenta.addContenido(test[i]);
                Cuenta.addTagHijo(NombreCuenta);
                
                Asiento.addTagHijo(Tercero);
                
                
                if(lleno==0){
                lleno=1;
                Debitos.addContenido(String.valueOf(valorguardar));
                Asiento.addTagHijo(Debitos);
                Asiento.addTagHijo(Creditosn);
                }else{
                lleno=0;
                Asiento.addTagHijo(Debitosn);
                Creditos.addContenido(String.valueOf(valorguardar));
                Asiento.addTagHijo(Creditos);
                }
                
                
            }
            String documentoxml =String.valueOf(n[0]);
            documentoxml+=".xml";
            JespXML xml = new JespXML(new File(documentoxml), Encoding.UTF_8);
        try {
            xml.escribirXML(comprobante);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(PruebaJespXML11.class.getName()).log(Level.SEVERE, null, ex);
        }
}

 
   public void start () {
    System.out.println("Iniciando " + nombreHilo );
    if (hilo == null) {
      hilo = new Thread (this, nombreHilo);
      hilo.run();
    }
  }

    public static void main(String[] args) throws InterruptedException {
        
        long startTime = System.currentTimeMillis();
         
        PruebaJespXML11 t1,t2,t3,t4;
        t1 = new PruebaJespXML11(1,"hilo 1");
        t2 = new PruebaJespXML11(1,"hilo 2");
        t3 = new PruebaJespXML11(1,"hilo 3");
        t4 = new PruebaJespXML11(1,"hilo 4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        long stopTime = System.currentTimeMillis();
         long elapsedTime = stopTime - startTime;
        System.out.println("tiempo : "+elapsedTime);
        }
         
    }
    


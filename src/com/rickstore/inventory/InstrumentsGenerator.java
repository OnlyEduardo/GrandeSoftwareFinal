package com.rickstore.inventory;

import com.rickstore.enumerators.Metal;
import com.rickstore.enumerators.TradeMark;
import com.rickstore.enumerators.Type;
import com.rickstore.enumerators.Wood;
import com.rickstore.instruments.*;
import com.rickstore.specs.Instrument;
import com.rickstore.util.ConsoleColors;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class InstrumentsGenerator {

    private static int quantityToGenerate;
    private static final List<Class<? extends Instrument>> instrumentsType = new ArrayList<>();

    static {
        try {
            List<Class<?>> classes = getClassesForPackage();

            for(Class<?> clazz: classes){
                //noinspection unchecked
                instrumentsType.add((Class<? extends Instrument>) clazz);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getInstrumentsToGenerate(){
        System.out.println("Quantos instrumentos você quer gerar para teste ?");

        System.out.println("Recomendado mínimo de " +
                ConsoleColors.RED + "10" + ConsoleColors.RESET +
                " e no máximo "+
                ConsoleColors.RED + "100000" + ConsoleColors.RESET +
                ", porém qualquer valor positivo é válido.");

        int valueToGen = 0;
        boolean niceNumber = false;

        while (!niceNumber){
            Scanner scanner = new Scanner(System.in);
            System.out.print(ConsoleColors.GREEN + "> " + ConsoleColors.RESET);

            try {
                valueToGen = scanner.nextInt();
            } catch (Exception ignored){
                System.out.println("Valor inválido, digite novamente.");
                continue;
            }

            if(valueToGen <= 0){
                System.out.println("Valor inválido, digite novamente.");
                continue;
            }

            niceNumber = true;
        }

        quantityToGenerate = valueToGen;
        generate();
    }

    private static void generate(){
        long serial = 1L;
        float price = generateNewPrice();

        int i = 0;
        int j = 0;

        List<Instrument> instrumentsList = new ArrayList<>();

        while ( i < quantityToGenerate) {

            Class<? extends Instrument> insClass = instrumentsType.get(j);

            switch (insClass.getSimpleName()) {
                case "Battery":
                    instrumentsList.add(new Battery(serial, price, TradeMark.getRandom(),
                            Type.getRandom(), Wood.getRandom(), Metal.getRandom()));
                    break;
                case "Flute":
                    if(new Random().nextFloat() > 0.5f){
                        instrumentsList.add(new Flute(serial, price, TradeMark.getRandom(),
                                Type.getRandom(), Wood.Nenhum, Metal.getRandom(), 7));
                    }
                    else {
                        instrumentsList.add(new Flute(serial, price, TradeMark.getRandom(),
                                Type.getRandom(), Wood.getRandom(), Metal.Nenhum, 6));
                    }
                    break;
                case "Guitar":
                    instrumentsList.add(new Guitar(serial, price, TradeMark.getRandom(),
                            Type.getRandom(), Wood.getRandom(), Wood.getRandom(),
                            (new Random().nextFloat() > 0.5f ? 6 : 7) ));
                    break;
                case "Mandolin":
                    instrumentsList.add(new Mandolin(serial, price, TradeMark.getRandom(),
                            Type.getRandom(), Wood.getRandom(), Wood.getRandom()));
                    break;
                case "Saxophone":
                    instrumentsList.add(new Saxophone(serial, price, TradeMark.getRandom(),
                            Type.getRandom(), Metal.getRandom()));
                    break;
                case "Violin":
                    instrumentsList.add(new Violin(serial, price, TradeMark.getRandom(),
                            Type.getRandom(), Wood.getRandom(), Wood.getRandom()));
                    break;
                default:
                    System.out.println("Classe inesperada encontrada no pacote: " + insClass.getSimpleName());
            }

            serial += 1L;
            price = generateNewPrice();
            j = ((1 + j) >= instrumentsType.size()) ? 0 : ++j;
            i++;
        }

        Inventory.allInstruments.addAll(instrumentsList);
    }

    private static float generateNewPrice(){
        return (float)((Math.random() * (5000 - 500)) + 500);
    }

    // Busca classes dinamicamente no pacote instruments
    private static void checkDirectory(File directory, String pckgname, ArrayList<Class<?>> classes) throws ClassNotFoundException {
        File tmpDirectory;

        if (directory.exists() && directory.isDirectory()) {
            final String[] files = directory.list();

            assert files != null;
            for (final String file : files) {
                if (file.endsWith(".class")) {
                    try {
                        classes.add(Class.forName(pckgname + '.'
                                + file.substring(0, file.length() - 6)));
                    } catch (final NoClassDefFoundError e) {
                        // do nothing. this class hasn't been found by the
                        // loader, and we don't care.
                    }
                } else if ((tmpDirectory = new File(directory, file))
                        .isDirectory()) {
                    checkDirectory(tmpDirectory, pckgname + "." + file, classes);
                }
            }
        }
    }

    private static void checkJarFile(JarURLConnection connection, ArrayList<Class<?>> classes) throws ClassNotFoundException, IOException {
        final JarFile jarFile = connection.getJarFile();
        final Enumeration<JarEntry> entries = jarFile.entries();
        String name;

        for (JarEntry jarEntry; entries.hasMoreElements()
                && ((jarEntry = entries.nextElement()) != null);) {
            name = jarEntry.getName();

            if (name.contains(".class")) {
                name = name.substring(0, name.length() - 6).replace('/', '.');

                if (name.contains("com.rickstore.instruments")) {
                    classes.add(Class.forName(name));
                }
            }
        }

    }

    private static ArrayList<Class<?>> getClassesForPackage() throws ClassNotFoundException {

        ArrayList<Class<?>> classes = new ArrayList<>();

        try {

            final ClassLoader cld = Thread.currentThread().getContextClassLoader();
            final Enumeration<URL> resources = cld.getResources("com.rickstore.instruments".replace('.', '/'));

            URLConnection connection;

            for (URL url; resources.hasMoreElements() && ((url = resources.nextElement()) != null);) {
                try {
                    connection = url.openConnection();

                    if (connection instanceof JarURLConnection) {
                        checkJarFile((JarURLConnection) connection,
                                classes);
                    } else if (connection != null) {
                        checkDirectory(new File(URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8)),
                                "com.rickstore.instruments", classes);
                    } else
                        throw new ClassNotFoundException("com.rickstore.instruments" + " ("
                                + url.getPath()
                                + ") does not appear to be a valid package");
                } catch (final IOException ioex) {
                    throw new ClassNotFoundException(
                            "IOException was thrown when trying to get all resources for "
                                    + "com.rickstore.instruments", ioex);
                }
            }
        } catch (final NullPointerException ex) {
            throw new ClassNotFoundException(
                    "com.rickstore.instruments"
                            + " does not appear to be a valid package (Null pointer exception)",
                    ex);
        } catch (final IOException ioex) {
            throw new ClassNotFoundException(
                    "IOException was thrown when trying to get all resources for "
                            + "com.rickstore.instruments", ioex);
        }

        return classes;
    }
}

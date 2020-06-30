/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents a main.User that can hold multiple libraries and ID
 *
 */
package main;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class User implements Iterable<Library> {
    private String aName;
    private String aPassword;
    private HashMap<String, Library> aLibraries;
    private static final String SAVE_FILE = "source/saves/save";

    public User() {
        aLibraries = new HashMap<String, Library>();
    }

    public User(String pName, String pPassword) {
        aName = pName;
        aPassword = pPassword;
        aLibraries = new HashMap<String, Library>();
    }

    public void addLibrary(Library library) {
        aLibraries.put(library.getName(), library);
    }

    public void removeLibrary(Library library) {
        aLibraries.remove(library.getName());
    }

    public Library getLibrary(String name) {
        return aLibraries.get(name);
    }

    public int getSize() {
        return aLibraries.size();
    }

    public boolean isEmpty() {
        return aLibraries.isEmpty();
    }

    public void displayLibraries() {
        aLibraries.forEach((k,v) -> v.displayCollection());
    }

    @Override
    public Iterator<Library> iterator() {
        return aLibraries.values().iterator();
    }

    /**
     * Saves information from this user to a file using ObjectStreams
     */
    public void saveFile() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream((new FileOutputStream(SAVE_FILE))));

            out.writeInt(getSize());
            for (Library library : this) {
                out.writeUTF(library.getName());
                out.writeInt(library.getSize());
                for (Alcohol alcohol : library) {
                    if (alcohol.getClass() == Wine.class) {
                        out.writeChar('W');
                    } else if (alcohol.getClass() == Beer.class) {
                        out.writeChar('B');
                    } else {
                        out.writeChar('A');
                    }
                    out.writeObject(alcohol);
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * Loads information from a file (same as saveFile method) to update this user
     */
    public void loadFile() {
        try {
            aLibraries.clear();

            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE)));
            int userSize = in.readInt();
            while (userSize > 0) {
                Library lib = new Library(in.readUTF());
                addLibrary(lib);
                int libSize = in.readInt();

                while (libSize > 0) {
                    char objectCode = in.readChar();
                    switch (objectCode) {
                        case 'W' :
                            Wine wine = (Wine) in.readObject();
                            lib.addAlcohol(wine);
                            break;
                        case 'B' :
                            Beer beer = (Beer) in.readObject();
                            lib.addAlcohol(beer);
                            break;
                        case 'A' :
                            Alcohol alcohol = (Alcohol) in.readObject();
                            lib.addAlcohol(alcohol);
                            break;
                        default : System.out.println("something went wrong");
                    }
                    libSize--;
                }
                userSize--;
            }
            in.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);

        } catch (EOFException e) {

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

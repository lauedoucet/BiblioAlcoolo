/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents a main.User that can hold multiple libraries and ID
 *
 */
package main;
import java.util.HashMap;
import java.util.Iterator;

public class User implements Iterable<Library> {
    /****************TODO: Implement as Singleton?****************/
    private String aName;
    private String aPassword;
    private HashMap<String, Library> aLibraries;

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
}

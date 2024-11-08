package outils;
import distances.*;
import temps.*;
import temperatures.*;

import java.lang.reflect.InvocationTargetException;

public class Factory {
    static public IUnite transformStringToClass(String u1)
    {
        IUnite unite = null;
        try
        {
            Class<?> classeDistance = Class.forName(u1);
            unite = (IUnite) classeDistance.getDeclaredConstructor().newInstance();

        } catch (ClassNotFoundException | NoClassDefFoundError  e) {
            System.out.println("Cette classe n'existe pas");
        } catch (ClassCastException  e) {
            System.out.println("Cette classe n'est pas une unite");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return unite;
    }
}

package plugin.nicohaxz.Utils;

import org.bukkit.persistence.PersistentDataType;
import plugin.nicohaxz.List.KeyList;

public class StormUtils {
    public static int getDuration(){

        if (PDC.GPDC.get(PDC.Key(KeyList.Permanoche), PDC.INT) == null){

            PDC.GPDC.set(PDC.Key(KeyList.Permanoche), PDC.INT,0);
            return 0;



        }else {
            return PDC.GPDC.get(PDC.Key(KeyList.Permanoche), PDC.INT);

        }


    }



    public static void setDuration(Integer value){
        PDC.GPDC.set(PDC.Key(KeyList.Permanoche), PDC.INT,value);}

    public static void setMaxValue(Integer value){
        PDC.GPDC.set(PDC.Key(KeyList.Permanoche), PDC.INT,value);}

    public static int getMaxValue(){

        if (PDC.GPDC.get(PDC.Key(KeyList.Permanoche), PersistentDataType.INTEGER) == null){

            PDC.GPDC.set(PDC.Key(KeyList.Permanoche), PDC.INT,0);
            return 0;



        }else {
            return (int) PDC.GPDC.get(PDC.Key(KeyList.Permanoche), PDC.INT);

        }


    }

    public static boolean isActive(){
        if (PDC.GPDC.get(PDC.Key(KeyList.Permanoche), PDC.INT)== null){

            PDC.GPDC.set(PDC.Key(KeyList.Permanoche), PDC.INT,0);
            return false;
        }else {


            if (getDuration() == 0){

                return false;
            }else {

                return true;
            }

        }
    }
}


package plugin.nicohaxz.Mobs;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import plugin.nicohaxz.Utils.ConfigData;
import plugin.nicohaxz.Utils.ListHandler;
import plugin.nicohaxz.Utils.PDC;
import plugin.nicohaxz.Utils.Utils;
import plugin.nicohaxz.main;

public class MobsUtils {
    public static void CustomMobEvent(Entity entity, String mob, int Day, Runnable code) {
        if (Utils.GetType(entity) == mob && ConfigData.getDay() >= Day ) {
            code.run();
        }
    }
    public static void setGhastExplosionRadius(int radius,Entity entity){
        PDC.EntityPDC(entity).set(PDC.Key(ListHandler.GhastExplosionRadius()),PDC.INT,radius);
    }
    public static int getGhastExplosionRadius(Entity entity){

        if (PDC.EntityPDC(entity).get(PDC.Key(ListHandler.mobtype()),PDC.STRING) == null){


            return 0;



        }else {

            return PDC.EntityPDC(entity).get(PDC.Key(ListHandler.GhastExplosionRadius()),PDC.INT);
        }



    }

    public static void setMobType(String mobType,Entity entity){
        PDC.EntityPDC(entity).set(PDC.Key(ListHandler.mobtype()),PDC.STRING,mobType);
    }
    public static String getTag(LivingEntity entity, String tag) {
        @NotNull PersistentDataContainer nbt = entity.getPersistentDataContainer();
        if (nbt != null) {
            if (nbt.get(new NamespacedKey(main.getInstance(), tag), PersistentDataType.STRING) != null) {
                return nbt.get(new NamespacedKey(main.getInstance(), tag), PersistentDataType.STRING);
            }
        }
        return null;
    }
    public static String getMobType(Entity entity){

        if (PDC.EntityPDC(entity).get(PDC.Key(ListHandler.mobtype()),PDC.STRING) == null){


            return "vanilla";



        }else {

            return PDC.EntityPDC(entity).get(PDC.Key(ListHandler.mobtype()), PDC.STRING);
        }



    }


    public static int valor(){

        if (Integer.valueOf(ConfigData.getDay()) >= 26){

            return 50;


        }
        return 20;}


    public static void BucleExplosionRage(Creeper creeper, int ExplosionRadius, boolean advanced) {
        final int[] totem = {ExplosionRadius};
        if (advanced == true) {

            new BukkitRunnable() {

                @Override
                public void run() {
                    Creeper creeper1 = creeper;

                    if (totem[0] >= valor()) {
                        cancel();
                    } else {
                        totem[0]++;
                        MobExplosion(creeper1, totem[0], true, true, false,creeper1.getName());

                    }
                }
            }.runTaskTimer(main.getPlugin(main.class), 0, 5L); //20 (ticks) = 1 segundoi


        } else {
            new BukkitRunnable() {

                @Override
                public void run() {
                    Creeper creeper1 = creeper;

                    if (totem[0] <= 0) {
                        cancel();
                    } else {
                        totem[0]--;
                        MobExplosion(creeper1, totem[0], true, true, false,creeper1.getName());

                    }
                }
            }.runTaskTimer(main.getPlugin(main.class), 0, 5L); //20 (ticks) = 1 segundoi
        }
    }
    public static void MobExplosion(Entity entity, int Yield, Boolean IsShielded, Boolean destroy, Boolean isProjectile,String DamagerName){
        if (isProjectile){
            if (IsShielded){
                if (destroy) {


                    Creeper creeper = (Creeper) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.CREEPER);
                    Projectile entity1 = (Projectile) entity;
                    LivingEntity entity2 = (LivingEntity) entity1.getShooter();
                    creeper.setInvisible(true);
                    creeper.setExplosionRadius(Yield);
                    creeper.setCustomName(DamagerName);
                    creeper.explode();

                }else {

                    Creeper creeper = (Creeper) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.CREEPER);
                    Projectile entity1 = (Projectile) entity;
                    LivingEntity entity2 = (LivingEntity) entity1.getShooter();
                    creeper.setInvisible(true);
                    creeper.setExplosionRadius(Yield);
                    creeper.setCustomName(DamagerName);
                    Bukkit.getWorld("world").setGameRule(GameRule.MOB_GRIEFING,Boolean.FALSE);
                    creeper.explode();
                    Bukkit.getWorld("world").setGameRule(GameRule.MOB_GRIEFING,Boolean.TRUE);

                }



            }else{
                if (destroy == true) {


                    Projectile entity1 = (Projectile) entity;
                    LivingEntity entity2 = (LivingEntity) entity1.getShooter();
                    entity1.getWorld().createExplosion(entity1.getLocation(), Yield, false, true);


                }else {


                    Projectile entity1 = (Projectile) entity;
                    LivingEntity entity2 = (LivingEntity) entity1.getShooter();
                    entity1.getWorld().createExplosion(entity1.getLocation(), Yield, false, false);
                }






            }

        }else {



            if (IsShielded == true){
                if (destroy == true) {


                    Creeper creeper = (Creeper) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.CREEPER);

                    creeper.setInvisible(true);
                    creeper.setExplosionRadius(Yield);
                    creeper.setCustomName(DamagerName);
                    creeper.explode();

                }else {

                    Creeper creeper = (Creeper) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.CREEPER);
                    ;
                    creeper.setInvisible(true);
                    creeper.setExplosionRadius(Yield);
                    creeper.setCustomName(DamagerName);
                    Bukkit.getWorld("world").setGameRule(GameRule.MOB_GRIEFING,Boolean.FALSE);
                    creeper.explode();
                    Bukkit.getWorld("world").setGameRule(GameRule.MOB_GRIEFING,Boolean.TRUE);

                }



            }else{
                if (destroy == true) {



                    entity.getWorld().createExplosion(entity.getLocation(), Yield, false, true);


                }else {



                    entity.getWorld().createExplosion(entity.getLocation(), Yield, false, false);
                }






            }


        }





    }
}



package plugin.nicohaxz.Mobs;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import plugin.nicohaxz.Utils.ListHandler;
import plugin.nicohaxz.Utils.PDC;
import plugin.nicohaxz.main;

public class MobBuilder {
    public static LivingEntity CreeperBuilder(Creeper creeper, String name, int yield, int health, int fuseTicks, float velocity, Boolean powered, int followRange, String customEntityType) {
        creeper.setExplosionRadius(yield);
        creeper.setCustomName(name);
        int maxFuse = Math.max(fuseTicks, 1);
        creeper.setMaxFuseTicks(maxFuse);
        creeper.setFuseTicks(Math.min(fuseTicks, maxFuse));
        creeper.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(velocity);
        creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            creeper.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        creeper.setHealth(creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        creeper.setPowered(powered);
        creeper.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        creeper.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "isCustom"), PersistentDataType.STRING, "yes");
        return creeper;
    }
    public static LivingEntity IronGolemBuilder(IronGolem ironGolem, String name, int health, float velocity, int followRange, int attack, String CustomEntityType){

        ironGolem.setCustomName(name);
        ironGolem.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(velocity);
        ironGolem.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        ironGolem.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attack);
        if (followRange != 0) {ironGolem.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);}
        ironGolem.setHealth(ironGolem.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        ironGolem.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"MobType"), PersistentDataType.STRING,CustomEntityType);
        ironGolem.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return ironGolem;
    }

    public static LivingEntity SkeletonBuilder(Skeleton skeleton, String name, int health, float velocity, int followRange, float knockbackResistance, String CustomEntityType){



        skeleton.setCustomName(name);
        skeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(velocity);
        skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        if (followRange != 0) {skeleton.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);}
        skeleton.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        skeleton.setHealth(skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        skeleton.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"MobType"), PersistentDataType.STRING,CustomEntityType);
        skeleton.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return skeleton;


    }
    public static LivingEntity RavagerBuilder(Ravager ravager, String name, int health, float velocity, int followRange, float knockbackResistance, float knockbackAmount, int attackTicks, int roarTicks, int stunTicks, String CustomEntityType){



        ravager.setCustomName(name);
        ravager.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(velocity);
        ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        if (followRange != 0) {ravager.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);}
        ravager.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        ravager.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(knockbackAmount);
        ravager.setHealth(ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        ravager.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"MobType"), PersistentDataType.STRING,CustomEntityType);
        ravager.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return ravager;
    }





    public static LivingEntity GhastBuilder(Ghast ghast,String name,int ExplosionRadius,int health, int followRange, String CustomEntityType){
        ghast.setCustomName(name);
        ghast.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        if (followRange != 0) {ghast.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);}
        ghast.setHealth(ghast.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        PDC.EntityPDC(ghast).set( PDC.Key(ListHandler.mobtype()),PDC.STRING,CustomEntityType);
        MobsUtils.setGhastExplosionRadius(ExplosionRadius, ghast);
        ghast.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return ghast;

    }

    public static LivingEntity ZombieBuilder(Zombie zombie,String name,int health, float velocity, int attack, int knockbackAmount, float knockbackResistance, int followRange, int MinionsChance, String CustomEntityType){
        zombie.setCustomName(name);
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(velocity);
        zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attack);
        zombie.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(knockbackAmount);
        zombie.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        if (followRange != 0) {zombie.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);}
        switch (MinionsChance) {
            case 1: {}
            case 2: {zombie.setBaby();}
            case 3: {zombie.setAdult();}
        }


        zombie.setHealth(zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        PDC.EntityPDC(zombie).set( PDC.Key(ListHandler.mobtype()),PDC.STRING,CustomEntityType);
        zombie.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return zombie;

    }
    public static LivingEntity VindicatorBuilder(Vindicator vindicator,String name,int health, float velocity, int attack, int knockbackAmount, float knockbackResistance, int followRange, String CustomEntityType){
        vindicator.setCustomName(name);
        vindicator.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        vindicator.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(velocity);
        vindicator.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attack);
        vindicator.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(knockbackAmount);
        vindicator.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        if (followRange != 0) {vindicator.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);}


        vindicator.setHealth(vindicator.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        PDC.EntityPDC(vindicator).set( PDC.Key(ListHandler.mobtype()),PDC.STRING,CustomEntityType);
        vindicator.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return vindicator;

    }
    public static LivingEntity SlimeBuilder(Slime slime, String name, int health, float velocity, int size, float knockbackResistance,  double followRange, String customEntityType) {
        slime.setCustomName(name);
        slime.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(velocity);
        slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        slime.setSize(size);
        slime.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        if (followRange != 0) {slime.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);}
        slime.setHealth(slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        slime.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        slime.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return slime;
    }
    public static LivingEntity MagmaCubeBuilder(MagmaCube magmaCube, String name, int health, float velocity, int size, float knockbackResistance,  double followRange, String customEntityType) {
        magmaCube.setCustomName(name);
        magmaCube.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(velocity);
        magmaCube.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        magmaCube.setSize(size);
        magmaCube.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        if (followRange != 0) {magmaCube.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);}
        magmaCube.setHealth(magmaCube.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        magmaCube.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        magmaCube.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return magmaCube;
    }
    public static LivingEntity BlazeBuilder(Blaze blaze, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance, String customEntityType) {
        blaze.setCustomName(name);
        blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            blaze.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        blaze.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        blaze.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        blaze.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        blaze.setHealth(blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        blaze.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        blaze.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return blaze;
    }
    public static LivingEntity EndermanBuilder(Enderman enderman, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance, double knockbackAmount, String customEntityType) {
        enderman.setCustomName(name);
        enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            enderman.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        enderman.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        enderman.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        enderman.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        enderman.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK).setBaseValue(knockbackAmount);
        enderman.setHealth(enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        enderman.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        enderman.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return enderman;
    }
    public static LivingEntity HoglinBuilder(Hoglin hoglin, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance, int conversionTime, boolean immuneToZombification, boolean ableToBeHunted, int MinionsChance, String customEntityType) {
        hoglin.setCustomName(name);
        hoglin.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            hoglin.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        hoglin.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        hoglin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        hoglin.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        hoglin.setHealth(hoglin.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

        hoglin.setConversionTime(conversionTime);
        hoglin.setImmuneToZombification(immuneToZombification);
        hoglin.setIsAbleToBeHunted(ableToBeHunted);
        switch (MinionsChance) {
            case 1: {}
            case 2: {hoglin.setBaby();}
            case 3: {hoglin.setAdult();}
        }

        hoglin.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        hoglin.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return hoglin;
    }
    public static LivingEntity PiglinBuilder(Piglin piglin, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance, boolean isImmuneToZombification, int MinionsChance, String customEntityType) {
        piglin.setCustomName(name);
        piglin.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            piglin.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        piglin.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        piglin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        piglin.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        piglin.setHealth(piglin.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

        switch (MinionsChance) {
            case 1: {}
            case 2: {piglin.setBaby();}
            case 3: {piglin.setAdult();}
        }
        piglin.setImmuneToZombification(isImmuneToZombification);

        piglin.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        piglin.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return piglin;
    }
    public static LivingEntity ZombifiedPiglinBuilder(PigZombie zombifiedPiglin, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance, int MinionsChance, String customEntityType) {
        zombifiedPiglin.setCustomName(name);
        zombifiedPiglin.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            zombifiedPiglin.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        zombifiedPiglin.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        zombifiedPiglin.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        zombifiedPiglin.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        zombifiedPiglin.setHealth(zombifiedPiglin.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

        switch (MinionsChance) {
            case 1: {}
            case 2: {zombifiedPiglin.setBaby();}
            case 3: {zombifiedPiglin.setAdult();}
        }

        zombifiedPiglin.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        zombifiedPiglin.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return zombifiedPiglin;
    }
    public static LivingEntity SpiderBuilder(Spider spider, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance  , String customEntityType) {
        spider.setCustomName(name);
        spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            spider.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        spider.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        spider.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        spider.setHealth(spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

        spider.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        spider.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return spider;
    }

    public static LivingEntity WardenBuilder(Warden warden, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance, Entity targetEntity, int angerDuration, Location disturbanceLocation, String customEntityType) {
        warden.setCustomName(name);
        warden.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            warden.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        warden.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        warden.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        warden.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        warden.setHealth(warden.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

        if (targetEntity != null && angerDuration > 0) {
            warden.setAnger(targetEntity, angerDuration);
        }

        if (disturbanceLocation != null) {
            warden.setDisturbanceLocation(disturbanceLocation);
        }

        warden.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        warden.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return warden;
    }
    public static LivingEntity BeeBuilder(Bee bee, String name, int health, double followRange, double attackDamage, double knockbackResistance, String customEntityType) {
        bee.setCustomName(name);
        bee.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            bee.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        bee.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        bee.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        bee.setHealth(bee.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

        bee.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        bee.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return bee;
    }
    public static LivingEntity WitherSkeletonBuilder(WitherSkeleton witherSkeleton, String name, int health, double followRange, double speed, double attackDamage, double knockbackResistance, String customEntityType) {
        witherSkeleton.setCustomName(name);
        witherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);

        if (followRange != 0) {
            witherSkeleton.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(followRange);
        }

        witherSkeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed);
        witherSkeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(attackDamage);
        witherSkeleton.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(knockbackResistance);
        witherSkeleton.setHealth(witherSkeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

        witherSkeleton.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(), "MobType"), PersistentDataType.STRING, customEntityType);
        witherSkeleton.getPersistentDataContainer().set(new NamespacedKey(main.getInstance(),"isCustom"), PersistentDataType.STRING,"yes");
        return witherSkeleton;
    }










    public static int RandomMinion() { return 1; }
    public static int AlwaysMinion() { return 2; }
    public static int AlwaysTall() { return 3; }


    public static void clearAnger(Warden warden) {
        warden.clearAnger(warden);
    }
    public static void increaseAnger(Warden warden, int increaseAmount) {
        warden.increaseAnger(warden, increaseAmount);
    }

    public static void addTag(LivingEntity entity, String tag, String value) {
        @NotNull PersistentDataContainer nbt = entity.getPersistentDataContainer();
        nbt.set(new NamespacedKey(main.getInstance(), tag), PersistentDataType.STRING, value);
    }
}


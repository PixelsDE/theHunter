/*     */
package Guns;
/*     */ 
/*     */

import HuntingMain.Main;
import Util.Settings;
import methods.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import ChestItems.Ammo;

import java.util.ArrayList;

/*     */ public class Minigun implements Listener
/*     */ {
    /*     */   private org.bukkit.craftbukkit.Main plugin;
    /*  68 */   private ArrayList<String> shotMinigun1 = new ArrayList();

    /*     */
    public Minigun(Main pluign)
/*     */ {
/*  31 */
        this.plugin = this.plugin;
/*     */
    }

    public static ItemStack minigun1() {
/*  71 */
        ItemStack item = new ItemStack(Material.IRON_HOE);
/*  72 */
        ItemMeta imeta = item.getItemMeta();
/*  73 */
        imeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fMinigun");
/*  74 */
        item.setItemMeta(imeta);
/*  75 */
        return item;
/*     */
    }

    public static void removeItem(PlayerInventory inv, Material type, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getItemMeta().equals(Ammo.MinigunAmmo().getItemMeta())) {
                int newamount = is.getAmount() - amount;
                if (newamount > 0) {
                    is.setAmount(newamount);
                    break;
                } else {
                    inv.remove(is);
                    amount = -newamount;
                    if (amount == 0)
                        break;
                }
            }
        }

    }

    @SuppressWarnings("deprecation")
	@EventHandler
/*     */ public void onInteract(PlayerInteractEvent e)/*     */ {
/*  81 */
        final Player p = e.getPlayer();
/*     */
/*  83 */
        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (p.getItemInHand().hasItemMeta()) {
/*  85 */
                if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(minigun1().getItemMeta().getDisplayName())) {
/*  86 */
/*     */
/*  86 */
                    if (!this.shotMinigun1.contains(p.getName()))/*     */ {
                    	 if (p.getInventory().containsAtLeast(Ammo.MinigunAmmo(), 1)) {
/*     */
                            try {
                                Snowball a = (Snowball) p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
                                a.setVelocity(p.getLocation().getDirection().multiply(3));
/*  91 */
                                p.getWorld().playSound(p.getLocation(), org.bukkit.Sound.EXPLODE, 1.0F, 1.0F);
                                this.shotMinigun1.add(p.getName());
                                a.setShooter(p);
                                /*  93 */
                             
                              /*  74 */
                                removeItem(p.getInventory(), Ammo.MinigunAmmo().getType(), 1);
                                p.updateInventory();
                            } catch (Exception exe) {
                                exe.printStackTrace();
                            }
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', Messages.cfg.getString("No_Ammo")));
                        }

                        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getPlugin(), new Runnable()
/*     */ {

                            public void run() {
                                shotMinigun1.remove(p.getName());
                            }
                        }, 20*Settings.cfg.getInt("Shoot_Minigun"));
                    } else
/*     */ {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', Messages.cfg.getString("Gun_Reload")));
                    }


                } else {

                }
            } else {

            }
        } else

        {

        }

    }


    @EventHandler
/*     */ public void onDamage(EntityDamageByEntityEvent e) {

        if (((e.getDamager() instanceof Snowball)) &&
/* 119 */       ((e.getEntity() instanceof LivingEntity))) {
/* 120 */
/* 120 */
            Snowball a = (Snowball) e.getDamager();
/* 121 */
            if ((a.getShooter() != null) &&
/* 122 */         ((a.getShooter() instanceof Player))) {
/* 123 */
                Player p = (Player) a.getShooter();
/* 124 */
                if ((p.getItemInHand().hasItemMeta())) {


/* 125 */
                    if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(minigun1().getItemMeta().getDisplayName())) {
/* 126 */
                        e.setDamage(Settings.cfg.getInt("Damage_Minigun"));
/*     */

                    } else {

                    }


                } else {

                }


            } else {

            }
/*     */
        } else {

        }
/*     */
    }

    @EventHandler
/*     */ public void onInteract1(PlayerInteractEvent e)/*     */ {
/*  81 */
        final Player p = e.getPlayer();
/*     */
/*  83 */
        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (p.getItemInHand().hasItemMeta()) {
/*  85 */
                if (p.getItemInHand().getType().equals(Material.SNOW_BALL)) {
/*  86 */


                    e.setCancelled(true);
                    p.updateInventory();
                } else {

                }
            } else {

            }
/*     */
        } else {

        }
    }
}


/* Location:              C:\Users\Daniel\Desktop\Daniel\Programmieren\Server\PixelsGun.jar!\Sniper\sniper1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
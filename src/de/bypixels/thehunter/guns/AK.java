/*     */
package de.bypixels.thehunter.guns;
/*     */
/*     */

import de.bypixels.thehunter.main.theHunterMain;
import de.bypixels.thehunter.chestitems.Ammo;
import de.bypixels.thehunter.util.Messages;
import de.bypixels.thehunter.util.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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

import java.util.ArrayList;

/*     */ public class AK implements Listener
        /*     */ {
    /*     */
    /*  68 */   private ArrayList<String> shotMG1 = new ArrayList();
    private ArrayList<Snowball> shoot = new ArrayList();

    /*     */ private org.bukkit.craftbukkit.Main plugin;
    public AK(theHunterMain pluign)
    /*     */ {
        /*  31 */
        this.plugin = plugin;
        /*     */
    }

    public static ItemStack ak1() {
        /*  71 */
        ItemStack item = new ItemStack(Material.GOLDEN_HOE);
        /*  72 */
        ItemMeta imeta = item.getItemMeta();
        /*  73 */

        imeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + "§fAK-47");
        /*  74 */
        item.setItemMeta(imeta);
        /*  75 */
        return item;
        /*     */
    }


    public static void removeItem(PlayerInventory inv, Material type, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is != null && is.getItemMeta().equals(Ammo.AKAmmo().getItemMeta())) {
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

        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (p.getItemInHand().hasItemMeta()) {
                /*  85 */
                if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ak1().getItemMeta().getDisplayName())) {
                    /*  86 */

                    if (!this.shotMG1.contains(p.getName()))/**/ {

                        ItemStack i = new ItemStack(Material.SNOWBALL);
                        ItemMeta im = i.getItemMeta();
                        im.setDisplayName(Ammo.AKAmmo().getItemMeta().getDisplayName());
                        i.setItemMeta(im);


                        if (p.getInventory().containsAtLeast(Ammo.AKAmmo(), 1)) {
                            try {
                                /*  89 */
                                Snowball a = (Snowball) p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
                                /*  90 */
                                a.setVelocity(p.getLocation().getDirection().multiply(5));
                                /*  91 */
                                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                                /*  92 */
                                this.shotMG1.add(p.getName());
                                this.shoot.add(a);
                                /*  93 */
                                a.setShooter(p);
                                removeItem(p.getInventory(), Ammo.AKAmmo().getType(), 1);

                                p.updateInventory();

                            } catch (Exception exe) {
                                exe.printStackTrace();
                            }
                            /*     */
                        } else {

                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', Messages.cfg.getString("No_Ammo")));
                        }


                        Bukkit.getScheduler().scheduleAsyncDelayedTask(theHunterMain.getPlugin(), new Runnable() {
                            public void run() {
                                /*  99 */
                                shotMG1.remove(p.getName());
                            }
                        }, 5 * Settings.cfg.getInt("Shoot_AK"));
                    } else
                        /*     */ {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Settings.cfg.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', Messages.cfg.getString("Gun_Reload")));
                        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
                    }


                } else {

                }
            } else {

            }
        } else {

        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    /*     */ public void onDamage(EntityDamageByEntityEvent e)/*     */ {

        if (((e.getDamager() instanceof Snowball)) && ((e.getEntity() instanceof Player))) {
            Snowball a = (Snowball) e.getDamager();

            Player shooter = (Player) a.getShooter();
            if (shooter.getItemInHand().equals(AK.ak1())){
                e.setDamage(Settings.cfg.getInt("Damage_AK")*2);

                shooter.playSound(shooter.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1 ,1);
                ((Player) e.getEntity()).playSound(e.getEntity().getLocation(), Sound.ENTITY_PLAYER_HURT, 1, 1);
            }
        }
        /*     */
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    /*     */ public void onInteract1(PlayerInteractEvent e)/*     */ {

        final Player p = e.getPlayer();

        if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            if (p.getItemInHand().hasItemMeta()) {
                /*  85 */
                if (p.getItemInHand().getType().equals(Material.MAGMA_CREAM)) {


                    e.setCancelled(true);
                    p.updateInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
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
package wolfy.frebinding.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY = "category.frebinding.special";
    public static Boolean ChunkBorder = false;
    public static Boolean Hitbox = false;

    public static KeyBinding showTooltips = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.frebinding.showTooltips",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            KEY_CATEGORY
    ));

    public static KeyBinding showHitboxes = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.frebinding.showHitboxes",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            KEY_CATEGORY
    ));

    public static KeyBinding showChunkBorder = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.frebinding.showChunkBorder",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_G,
            KEY_CATEGORY
    ));

    public static KeyBinding reloadResources = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.frebinding.reloadResources",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Y,
            KEY_CATEGORY
    ));

    public static KeyBinding clearChat = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.frebinding.clearChat",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_U,
            KEY_CATEGORY
    ));

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            /* Show Tooltips */
            if (showTooltips.wasPressed()) {
                client.options.advancedItemTooltips = !client.options.advancedItemTooltips;
                if (client.options.advancedItemTooltips) client.player.sendMessage(Text.translatable("showTooltips.enabled"), false);
                else client.player.sendMessage(Text.translatable("showTooltips.disabled"), false);
            }

            /* Show Hitboxes */
            if (showHitboxes.wasPressed()) {
                EntityRenderDispatcher entityRenderDispatcher = client.getEntityRenderDispatcher();
                if (entityRenderDispatcher != null) {
                    Hitbox = !Hitbox;
                    entityRenderDispatcher.setRenderHitboxes(Hitbox);
                    if (Hitbox) client.player.sendMessage(Text.translatable("showHitboxes.enabled"), false);
                    else client.player.sendMessage(Text.translatable("showHitboxes.disabled"), false);
                }
            }

            /* Show Chunk Border */
            if (showChunkBorder.wasPressed()) {
                ChunkBorder = !ChunkBorder;
                client.debugRenderer.toggleShowChunkBorder();
                if (ChunkBorder) client.player.sendMessage(Text.translatable("showChunkBorder.enabled"), false);
                else client.player.sendMessage(Text.translatable("showChunkBorder.disabled"), false);
            }

            /* Reload Textures */
            if (reloadResources.wasPressed()) {
                client.reloadResources();
                client.player.sendMessage(Text.translatable("reloadResources.reloaded"), false);
            }

            /* Clear Chat */
            if (clearChat.wasPressed()) {
                client.inGameHud.getChatHud().clear(true);
            }
        });
    }
}

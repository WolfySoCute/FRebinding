package wolfy.frebinding.client;

import net.fabricmc.api.ClientModInitializer;
import wolfy.frebinding.event.KeyInputHandler;

public class FRebindingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}

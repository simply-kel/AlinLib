package ru.kelcuprum.alinlib;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import ru.kelcuprum.alinlib.command.AlinLibCommand;

public class AlinLibTest implements ClientModInitializer {
    public static final String MODID = "alinlibtest";

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(AlinLibCommand::register);
    }
}
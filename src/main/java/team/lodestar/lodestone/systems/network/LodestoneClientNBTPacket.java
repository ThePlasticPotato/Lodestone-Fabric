package team.lodestar.lodestone.systems.network;

import me.pepperbell.simplenetworking.S2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

public abstract class LodestoneClientNBTPacket implements S2CPacket {
    protected CompoundTag data;

    public LodestoneClientNBTPacket(CompoundTag data) {
        this.data = data;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeNbt(data);
    }

}
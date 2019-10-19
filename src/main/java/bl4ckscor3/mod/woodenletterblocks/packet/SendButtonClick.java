package bl4ckscor3.mod.woodenletterblocks.packet;

import java.util.function.Supplier;

import bl4ckscor3.mod.woodenletterblocks.Type.Letter;
import bl4ckscor3.mod.woodenletterblocks.client.LetterButton;
import bl4ckscor3.mod.woodenletterblocks.container.CreatorContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class SendButtonClick
{
	private int letterOrdinal;

	public SendButtonClick() {}

	public SendButtonClick(LetterButton button)
	{
		this.letterOrdinal = button.letter.ordinal();
	}

	public void toBytes(PacketBuffer buf)
	{
		buf.writeInt(letterOrdinal);
	}

	public void fromBytes(PacketBuffer buf)
	{
		letterOrdinal = buf.readInt();
	}

	public static void encode(SendButtonClick message, PacketBuffer packet)
	{
		message.toBytes(packet);
	}

	public static SendButtonClick decode(PacketBuffer packet)
	{
		SendButtonClick message = new SendButtonClick();

		message.fromBytes(packet);
		return message;
	}

	public static void onMessage(SendButtonClick message, Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() -> {
			PlayerEntity player = ctx.get().getSender();
			int letterOrdinal = message.letterOrdinal;

			if(player != null && player.openContainer instanceof CreatorContainer && letterOrdinal > 0 && letterOrdinal < 26)
			{
				CreatorContainer container = (CreatorContainer)player.openContainer;

				container.letter = Letter.values()[letterOrdinal];
				container.updateOutput(container.getInv());
			}
		});

		ctx.get().setPacketHandled(true);
	}
}

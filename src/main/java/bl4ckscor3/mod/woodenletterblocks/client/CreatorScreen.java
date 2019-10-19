package bl4ckscor3.mod.woodenletterblocks.client;

import bl4ckscor3.mod.woodenletterblocks.Type.Letter;
import bl4ckscor3.mod.woodenletterblocks.WoodenLetterBlocks;
import bl4ckscor3.mod.woodenletterblocks.container.CreatorContainer;
import bl4ckscor3.mod.woodenletterblocks.packet.SendButtonClick;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button.IPressable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CreatorScreen extends ContainerScreen<CreatorContainer>
{
	public static final ResourceLocation TEXTURE = new ResourceLocation(WoodenLetterBlocks.MODID, "textures/gui/container/creator.png");
	private LetterButton selectedButton;

	public CreatorScreen(CreatorContainer screenContainer, PlayerInventory inv, ITextComponent title)
	{
		super(screenContainer, inv, title);

		xSize = 196;
		ySize = 182;
	}

	@Override
	protected void init()
	{
		super.init();

		final int size = 15;
		final int xStart = guiLeft + 79;
		int x = xStart;
		int y = guiTop + 20;
		final IPressable onPress = button -> {
			if(selectedButton != null)
				selectedButton.active = true;

			button.active = false;
			selectedButton = (LetterButton)button;
			WoodenLetterBlocks.channel.sendToServer(new SendButtonClick(selectedButton));

			Container c = minecraft.player.openContainer;

			if(c instanceof CreatorContainer)
			{
				((CreatorContainer)c).letter = selectedButton.letter;
				((CreatorContainer)c).updateOutput(((CreatorContainer)c).getInv());
			}
		};

		for(int i = 0; i < Letter.values().length; i++)
		{
			if(i % 7 == 0 && i != 0)
			{
				x = xStart;
				y += size;
			}

			addButton(new LetterButton(x, y, size, size, onPress, Letter.values()[i]));
			x += size;
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		int xPos = (width - xSize) / 2;
		int yPos = (height - ySize) / 2;

		renderBackground();
		minecraft.getTextureManager().bindTexture(TEXTURE);
		blit(xPos, yPos, 0, 0, xSize, ySize);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks)
	{
		super.render(mouseX, mouseY, partialTicks);

		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		String title = getTitle().getFormattedText();

		font.drawString(title, (xSize / 2) - (font.getStringWidth(title) / 2), 5, 0x404040);
		font.drawString(playerInventory.getDisplayName().getFormattedText(), 18, ySize - 93, 0x404040);
	}
}

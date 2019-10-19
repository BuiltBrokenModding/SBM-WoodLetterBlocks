package bl4ckscor3.mod.woodenletterblocks.client;

import bl4ckscor3.mod.woodenletterblocks.Type.Letter;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class LetterButton extends GuiButtonExt
{
	public final Letter letter;

	public LetterButton(int xPos, int yPos, int width, int height, IPressable handler, Letter letter)
	{
		super(xPos, yPos, width, height, letter.name(), handler);

		this.letter = letter;
	}
}

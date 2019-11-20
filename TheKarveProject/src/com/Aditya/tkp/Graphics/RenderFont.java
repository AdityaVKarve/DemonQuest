package com.Aditya.tkp.Graphics;

public class RenderFont {
	
	private static SpriteSheet font=new SpriteSheet("/fonts/arial.png",16);	
	public static Sprite[] characters=Sprite.split(font);
	private static String charIndex="ABCDEFGHIJKLM" + //
			"NOPQRSTUVWXYZ" + //
			"abcdefghijklm" + //
			"nopqrstuvwxyz" + //
			"0123456789.,'" + //	
			"'“”;:!@$%()-+";
	public RenderFont()
	{
		
	}
	public void render(int x,int y,int spacing,String text,int color,Screen screen)
	{
		int index=0; 
		int lineCorrect=3;
		int ctr=0;
		for(int i=0;i<text.length();i++)
		{
			ctr++;
			char currentChar=text.charAt(i);
			if(currentChar==' ')
			{
				continue;
			}
			if(currentChar=='\n')
			{
					y+=16;
					ctr=0;
					continue;
			}
			index=charIndex.indexOf(currentChar);
			if(currentChar=='g'|currentChar=='y'|currentChar=='j'|currentChar=='.'|currentChar==',')	
				screen.renderTextCharacter(x+(spacing+16)*ctr,y+lineCorrect, characters[index],color, false);
			else
			{
				screen.renderTextCharacter(x+(spacing+16)*ctr,y, characters[index],color, false);
			}

		}	
	}
}


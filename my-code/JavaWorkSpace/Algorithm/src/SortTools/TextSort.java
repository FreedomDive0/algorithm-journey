package SortTools;

import java.util.Arrays;

public class TextSort {
	public static void main(String[] args) {
		String[] str = new String[] { "AddCardToDrawPile", "CardExit", "CardEnter", "CardAttack", "ChangeCardStatus",
				"CardHealthChange", "CardShieldChange", "ChangeCardAbility", "ChangeActionPoint", "ChangeCostModifier",
				"ChangeSlotCardState", "DrawCard", "EndDuel", "EndRound", "EndRoundStatusConsume", "RemoveHandCard",
				"StartRound", "StartOfTurnDraw", "Shuffle", "UseCard" };
		Arrays.sort(str);
		for (String s : str) {
			System.out.println(s);
		}
	}
}

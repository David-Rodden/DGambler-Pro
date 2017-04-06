package sample;

import org.tbot.methods.Time;
import org.tbot.methods.Widgets;
import org.tbot.wrappers.Player;
import org.tbot.wrappers.WidgetChild;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Optional;

/**
 * Created by David on 3/2/2017.
 * Facilitates trading with static calls
 */
public class TradeUtils {
    public static final int COINS = 995;
    public static final int NONE = 0;
    public static final int TRADE_INIT_TIMEOUT = 4000, TRADE_INIT_TIMEOUT_SEC = 4;
    public static final int TRADING_TIMEOUT = 30000, TRADING_TIMEOUT_SEC = 30;
    public static final int INVENTORY_EMPTY = 0, INVENTORY_MAX = 28;
    public static final Hashtable<String, WidgetChild> widgets;

    static {
        widgets = new Hashtable<>();
    }

    /**
     * Initialization must be called before any other static calls can be made
     * Widgets are pushed to a global widget stack which allows for calling
     */
    public void init() {
        widgets.put("first", Widgets.getWidget(335, 2));
        widgets.put("second", Widgets.getWidget(334, 1));
        widgets.put("first with", Widgets.getWidget(335, 31));
        widgets.put("second with", Widgets.getWidget(334, 30));
        widgets.put("first accept", Widgets.getWidget(335, 12));
        widgets.put("second accept", Widgets.getWidget(334, 13));
        widgets.put("first decline", Widgets.getWidget(335, 15));
        widgets.put("second decline", Widgets.getWidget(334, 14));
        widgets.put("my first offer", Widgets.getWidget(335, 25));
        widgets.put("my second offer", Widgets.getWidget(334, 28));
        widgets.put("other first offer", Widgets.getWidget(335, 28));
        widgets.put("other second offer", Widgets.getWidget(334, 29));
    }


    /**
     *
     * @param message
     * @return String
     */
    public static String getTraderFromMessage(final String message) {
        final String tradeMessage = "wishes to trade with you.";
        return message.matches("[\\w\\s]+ " + tradeMessage) ? message.split(tradeMessage)[0].trim() : null;
    }

    public static void tradeWith(final Player player) {
        if (player == null) return;
        player.interact("Trade with");
        Time.sleepUntil(() -> TradeUtils.isTrading(), TRADE_INIT_TIMEOUT);
    }

    public static boolean isFirstTradeOpen() {
        return widgets.get("first").isVisible();
    }

    public static boolean isSecondTradeOpen() {
        return widgets.get("second").isVisible();
    }

    public static boolean isTrading() {
        return isFirstTradeOpen() || isSecondTradeOpen();
    }

    public static boolean isTradingWith(final String name) {
        if (!isTrading()) return false;
        return widgets.get("first with").getText().matches(".*with " + name) || widgets.get("second with").getText().matches(".*<br>" + name);
    }

    public static int getOtherInventorySize() {
        if (!isFirstTradeOpen() || isSecondTradeOpen()) return 0;
        final String[] widgetText = widgets.get("first free slots").getText().split("has");
        return INVENTORY_MAX - Integer.parseInt(widgetText[widgetText.length - 1].replaceAll("\\D+", ""));
    }

    public static boolean isOtherInventoryEmpty() {
        return getOtherInventorySize() == INVENTORY_EMPTY;
    }

    private static int getAmountOffered(final boolean byMe, final int id) {
        final Optional<WidgetChild> idOfferedFirst = Arrays.stream(widgets.get(byMe ? "my first offer" : "other first offer").getChildren()).filter(o -> o.getItemID() == id).findAny();
        final Optional<WidgetChild> idOfferedSecond = Arrays.stream(widgets.get(byMe ? "my second offer" : "other second offer").getChildren()).filter(o -> o.getItemID() == id).findAny();
        return idOfferedFirst.isPresent() ? idOfferedFirst.get().getItemStackSize() : idOfferedSecond.isPresent() ? idOfferedSecond.get().getItemStackSize() : NONE;
    }

    public static int getCoinsOffered(final boolean byMe) {
        return getAmountOffered(byMe, COINS);
    }

    public static void declineTrade() {
        if (!isTrading()) return;
        widgets.get(isFirstTradeOpen() ? "first decline" : "second decline").click();
        Time.sleepUntil(() -> !isTrading(), TRADE_INIT_TIMEOUT);
    }

    public static boolean isMyAcceptPending() {
        if (!isTrading()) return false;
        //complete method with widgets
        return true;
    }

    public static void acceptTrade() {
        if (!isTrading()) return;
        final boolean firstTradeOpen = isFirstTradeOpen();
        widgets.get(firstTradeOpen ? "first accept" : "second accept").click();
        Time.sleepUntil(() -> firstTradeOpen ? isSecondTradeOpen() : !isTrading(), TRADE_INIT_TIMEOUT);
    }
}

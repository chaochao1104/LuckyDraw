package controller.strategy.factory;

import java.util.EnumMap;
import java.util.Map;

import model.DrawStrategy.DrawStrategyType;
import controller.strategy.DrawListener;
import controller.strategy.impl.OnceDrawListener;
import controller.strategy.impl.OneByOneDrawListener;


public class DrawListenerFactory {
	
	private static Map<DrawStrategyType, DrawListener> typeIndexedDrawStrategy;
	
	static {
		typeIndexedDrawStrategy = new EnumMap<DrawStrategyType, DrawListener>(DrawStrategyType.class);
		OneByOneDrawListener oneByOneDrawListener = new OneByOneDrawListener();
		typeIndexedDrawStrategy.put(oneByOneDrawListener.getType(), oneByOneDrawListener);
		
		OnceDrawListener onceDrawListener = new OnceDrawListener();
		typeIndexedDrawStrategy.put(onceDrawListener.getType(), onceDrawListener);
	}
	
	public static DrawListener strategyInstance(DrawStrategyType type) {
		DrawListener drawStrategy = typeIndexedDrawStrategy.get(type);
		if (drawStrategy == null)
			throw new IllegalArgumentException("no such strategy type:" + type);
		
		return drawStrategy;
	}
	
}

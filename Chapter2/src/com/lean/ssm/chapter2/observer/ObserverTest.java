package com.lean.ssm.chapter2.observer;

public class ObserverTest {
	
	public static void main(String[] args) {
		ProductList observable = ProductList.getInstance();
		TaoBaoObserver taoBaoObserver = new TaoBaoObserver();
		JingDongObserver jdObserver = new JingDongObserver();
		observable.addObserver(jdObserver);
		observable.addObserver(taoBaoObserver);
		observable.addProudct("新增产品1");
	}
}

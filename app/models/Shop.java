package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

public class Shop {
		
		SortedMap<Long, Item> items = new ConcurrentSkipListMap<>();
		AtomicLong seq = new AtomicLong();
		
		public Collection<Item> list() {
			return new ArrayList<>(items.values());
		}

		public Item create(String name, Double price) {
			Long id = seq.incrementAndGet();
			Item item = new Item(id, name, price);
			items.put(id, item);
			return item;
		}
		
		public Item get(Long id) {
			return items.get(id);
		}
		
		public synchronized Item update(Long id, String name, Double
				price) {
			Item item = items.get(id);
			if (item != null) {
				Item updated = new Item(id, name, price);
				items.put(id, updated);
				return updated;
			} else return null;
		}
		
		public Boolean delete(Long id) {
			return items.remove(id) != null;
		}
}

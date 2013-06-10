package net.ellwein.routey.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Internal regex lookup map.
 * 
 * @author Alexander Ellwein
 * @since 1.0.0
 * 
 * @param <K>
 *            key
 * @param <V>
 *            value
 */
public class RegexLookupMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 1L;

	private final List<Pattern> patternList = new ArrayList<Pattern>();
	private final List<V> valuesList = new ArrayList<V>();

	@Override
	public V get(final Object key) {
		if (key == null) {
			throw new IllegalArgumentException("key may not be null");
		}
		for (int i = 0; i < patternList.size(); i++) {
			if (patternList.get(i).matcher(key.toString()).matches()) {
				return valuesList.get(i);
			}
		}
		return null;
	}

	@Override
	public V put(final K key, final V value) {
		if (key == null) {
			throw new IllegalArgumentException("key may not be null");
		}
		final Pattern p = Pattern.compile(key.toString());
		if (!patternList.contains(p)) {
			patternList.add(p);
			valuesList.add(value);
		}
		return value;
	}

}

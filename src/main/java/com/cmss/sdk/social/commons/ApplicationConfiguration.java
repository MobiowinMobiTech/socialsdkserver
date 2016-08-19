package com.cmss.sdk.social.commons;

import java.util.Map;
import java.util.Set;

public class ApplicationConfiguration<K, V>
{
	private Map<K, V> configMap;

	public ApplicationConfiguration()
	{
	}

	public void setConfigMap(Map<K, V> configMap)
	{
		this.configMap = configMap;
	}

	public V getValue(K key)
	{
		return configMap.get(key);
	}

	public void setValueForKey(K key, V value)
	{
		configMap.put(key, value);
	}

	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicationConfiguration [");

		Set<K> keySet = configMap.keySet();

		for (K key : keySet)
		{
			builder.append("\n\t" + key.toString() + " : "
					+ configMap.get(key).toString());
		}

		builder.append("\n]");

		return builder.toString();
	}
}

package com.automationanywhere.botcommand.sk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.BooleanValue;
import com.automationanywhere.botcommand.data.impl.NumberValue;
import com.automationanywhere.botcommand.data.impl.StringValue;

public class DMNUtils {
	
	static List<Value> executeDMN(String code,Map<String,Value> input, String decisionEntry,Boolean isCode) throws Exception {
		
		Map<String, Object> variables = new HashMap<String,Object>();
		for ( Entry<String, Value> value : input.entrySet()) {
			String classname = value.getValue().getClass().getSimpleName();
			switch (classname) {
				case "StringValue":
					variables.put(value.getKey(), ((StringValue)value.getValue()).get());
					break;
				case "NumberValue":
					variables.put(value.getKey(), ((NumberValue)value.getValue()).get());
					break;
				case "BooleanValue":
					variables.put(value.getKey(), ((BooleanValue)value.getValue()).get());
					break;
				default:
					break;
			}
		}
		
		DmnEngine dmnEngine = DmnEngineConfiguration
			      .createDefaultDmnEngineConfiguration()
			      .buildEngine();
		
		
		InputStream inputStream ;
		
		if (isCode) {
			inputStream = new ByteArrayInputStream(code.getBytes());
		}
		else {
			inputStream = new FileInputStream(new File(code));
		}
		

		DmnDecision decision = dmnEngine.parseDecision(decisionEntry, inputStream);
	
		DmnDecisionTableResult resultTable = dmnEngine.evaluateDecisionTable(decision, variables);
		
		List <Value> list = new ArrayList<Value>();
		if (resultTable.size() == 1)
		{
			list.add(new StringValue(resultTable.getSingleResult().getFirstEntry().toString()));
		}
		else
		{	
		   List<Object> templist = resultTable.collectEntries(decisionEntry);
		   for (Iterator iterator = templist.iterator(); iterator.hasNext();) {
			   Object object = (Object) iterator.next();
			   list.add(new StringValue(object.toString()));
		   }
		
		}
		
		return list;
		
	}
}

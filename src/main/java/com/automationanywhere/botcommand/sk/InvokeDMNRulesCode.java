
package com.automationanywhere.botcommand.sk;


import java.util.List;
import java.util.Map;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.AttributeType;
import com.automationanywhere.commandsdk.model.DataType;
import com.automationanywhere.commandsdk.annotations.Execute;


/**
 * @author Stefan Karsten
 *
 */

@BotCommand
@CommandPkg(label = "Evaluate DMN Rules Code", name = "evaldmnrulescode",
        description = "Evaluate DMN Rules as Code String",
        node_label = "Evaluate DMN Rules Code", icon = "pkg.svg" , comment = true ,  text_color = "#df3e75" , background_color =  "#df3e75" ,
        return_type=DataType.LIST, return_sub_type=DataType.STRING ,return_label="Rule Results", return_required=true)
public class InvokeDMNRulesCode {
	   
	@Execute
    public ListValue<String> action(@Idx(index = "3", type = AttributeType.CODE)  @Pkg(label = "DMN Code"  , default_value_type = DataType.FILE) @NotEmpty String DMNCode,
    								@Idx(index = "2", type = AttributeType.DICTIONARY)  @Pkg(label = "Dictionary with input values"  , default_value_type = DataType.DICTIONARY ) @NotEmpty Map<String,Value> inputdict,
    								@Idx(index = "1", type = AttributeType.TEXT)  @Pkg(label = "Rule Set"  , default_value_type = DataType.STRING ) @NotEmpty String ruleset
                                      ) throws Exception
     {

		List<Value> result = DMNUtils.executeDMN(DMNCode, inputdict, ruleset,true);
		
		ListValue<String> listvalue = new ListValue<String>();
		listvalue.set(result);
		
		return listvalue;
		
				

	}

		
	
}
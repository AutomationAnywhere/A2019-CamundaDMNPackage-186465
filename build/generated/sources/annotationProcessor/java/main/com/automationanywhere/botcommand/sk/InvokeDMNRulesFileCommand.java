package com.automationanywhere.botcommand.sk;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class InvokeDMNRulesFileCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(InvokeDMNRulesFileCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    InvokeDMNRulesFile command = new InvokeDMNRulesFile();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("DMNfile") && parameters.get("DMNfile") != null && parameters.get("DMNfile").get() != null) {
      convertedParameters.put("DMNfile", parameters.get("DMNfile").get());
      if(!(convertedParameters.get("DMNfile") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","DMNfile", "String", parameters.get("DMNfile").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("DMNfile") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","DMNfile"));
    }

    if(parameters.containsKey("inputdict") && parameters.get("inputdict") != null && parameters.get("inputdict").get() != null) {
      convertedParameters.put("inputdict", parameters.get("inputdict").get());
      if(!(convertedParameters.get("inputdict") instanceof Map)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","inputdict", "Map", parameters.get("inputdict").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("inputdict") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","inputdict"));
    }

    if(parameters.containsKey("ruleset") && parameters.get("ruleset") != null && parameters.get("ruleset").get() != null) {
      convertedParameters.put("ruleset", parameters.get("ruleset").get());
      if(!(convertedParameters.get("ruleset") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","ruleset", "String", parameters.get("ruleset").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("ruleset") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","ruleset"));
    }

    try {
      Optional<Value> result =  Optional.ofNullable(command.action((String)convertedParameters.get("DMNfile"),(Map<String, Value>)convertedParameters.get("inputdict"),(String)convertedParameters.get("ruleset")));
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}

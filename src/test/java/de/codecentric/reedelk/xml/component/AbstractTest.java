package de.codecentric.reedelk.xml.component;

import de.codecentric.reedelk.runtime.api.component.Component;
import de.codecentric.reedelk.runtime.api.converter.ConverterService;
import de.codecentric.reedelk.runtime.api.flow.FlowContext;
import de.codecentric.reedelk.runtime.api.script.ScriptEngineService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
abstract class AbstractTest {

    @Mock
    protected FlowContext context;
    @Mock
    protected ConverterService converterService;
    @Mock
    protected ScriptEngineService scriptEngineService;

    protected void setUpScriptEngineService(Component component) {
        setComponentFieldWithObject(component, "scriptEngine", scriptEngineService);
    }

    protected void setUpMockConverterService(Component component) {
        lenient().when(converterService.convert(any(Object.class), eq(byte[].class))).thenAnswer(invocation -> {
            String actualValue = invocation.getArgument(0);
            return actualValue.getBytes();
        });
        setComponentFieldWithObject(component, "converterService", converterService);
    }

    protected void setComponentFieldWithObject(Component component, String field, Object object) {
        try {
            Field converterServiceField = component.getClass().getDeclaredField(field);
            converterServiceField.setAccessible(true);
            converterServiceField.set(component, object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            fail(e.getMessage(), e);
        }
    }
}

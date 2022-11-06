package org.example.dependent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyServiceTest {
    private static final String MESSAGE = "Message";
    private final Client client = mock(Client.class);
    private final MyService myService = new MyService(client);

    @BeforeEach
    void setupMock() {
        reset(client);
    }

    @Test
    void checkClientIsHealthy_shouldReturnTrue_whenClientHealthy() {
        //given
        when(client.checkHealth()).thenReturn(Boolean.TRUE);
        //when then
        assertTrue(myService.checkClientIsHealthy());
    }

    @Test
    void checkClientIsHealthy_shouldReturnFalse_whenClientNotHealthy() {
        //given
        when(client.checkHealth()).thenReturn(Boolean.FALSE);
        //when then
        assertFalse(myService.checkClientIsHealthy());
    }

    @Test
    void performAction_shouldReturnResponse_whenClientHealthyAndResponseFilled() {
        //given
        when(client.checkHealth()).thenReturn(Boolean.TRUE);
        when(client.call()).thenReturn(MESSAGE);
        //when
        String response = myService.performAction();
        //then
        assertEquals(MESSAGE, response);
    }

    @Test
    void performAction_shouldThrowException_whenClientNotHealthy() {
        //given
        when(client.checkHealth()).thenReturn(Boolean.FALSE);
        //when //then
        assertThrows(RuntimeException.class, myService::performAction);
    }

    @Test
    void performAction_shouldThrowException_whenClientThrowsDuringHealthCheck() {
        //given
        when(client.checkHealth()).thenThrow(RuntimeException.class);
        //when //then
        assertThrows(RuntimeException.class, myService::performAction);
    }

    @Test
    void performAction_shouldThrowException_whenClientHealthyAndThrowsDuringCall() {
        //given
        when(client.checkHealth()).thenReturn(Boolean.TRUE);
        when(client.call()).thenThrow(RuntimeException.class);
        //when //then
        assertThrows(RuntimeException.class, myService::performAction);
    }

    @Test
    void clean_shouldInvokeClientClean() {
        //when
        myService.clean();
        // then
        verify(client, times(1)).performClean();
    }

    @Test
    void clean_shouldThrowException_whenClientCleanThrows() {
        //given
        doThrow(new RuntimeException()).when(client).performClean();
        assertThrows(RuntimeException.class, myService::clean);
    }
}
package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AdocaoService service;


    @Test
    void deveriaDevolverCodigo400ParaSolicitacaoComErros() throws Exception {
        //Arrance
         String json = "{}";

        //Act
         var response  = mvc.perform(
                 post("/adocoes")
                         .content(json)
                         .contentType(MediaType.APPLICATION_JSON)
         ).andReturn().getResponse();

        //Assert
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaSolicitacaoDeAdocaoSemErros() throws Exception {
        //Arrance
        String json = """
                {
                    "idPet": 1,
                    "idTutor": 1,
                    "motivo": "Motivo qualquer"
                }
                """;

        //Act
        var response  = mvc.perform(
                post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaRequisicaoDeAprovarAdocao() throws Exception {
        //Arrance
        String json = """
                {
                    "idAdocao": 1
                }
                """;

        //Act
        var response  = mvc.perform(
                put("/adocoes/aprovar")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaRequicaoDeAprovarAdocaoInvalida() throws Exception {
        //Arrance
        String json = """
                {
                
                }
                """;

        //Act
        var response  = mvc.perform(
                put("/adocoes/aprovar")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaRequisocaoDeReprovarAdocao() throws Exception {
        //Arrance
        String json = """
                {
                    "idAdocao": 1,
                    "justificativa": "qualquer"
                }
                """;

        //Act
        var response  = mvc.perform(
                put("/adocoes/aprovar")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaRequicaoDeReprovarAdocaoInvalida() throws Exception {
        //Arrance
        String json = """
                {
                
                }
                """;

        //Act
        var response  = mvc.perform(
                put("/adocoes/reprovar")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        Assertions.assertEquals(400, response.getStatus());
    }

}
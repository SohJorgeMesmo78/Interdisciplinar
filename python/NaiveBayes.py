from unidecode import unidecode
import pandas as pd
import math

class NaiveBayes:
    def __init__(self):
        data = pd.read_csv("files/nomes.csv", sep=',')
        self.df = pd.DataFrame(data)
        self.df_masc = [[0 for i in range(3)] for j in range(26)]
        self.df_fem = [[0 for i in range(3)] for j in range(26)]
        self.incidencia_masc = [[0 for i in range(4)] for j in range(26)]
        self.incidencia_fem = [[0 for i in range(4)] for j in range(26)]
    
    # Conta a incidencia de cada letra no genero informado
    def conta_dados(self, genero, incidencia):
        for i in range(self.df.shape[0]):
            if (self.df.loc[i][1] == genero):
                # Pega a primeira, penultima e ultima letra do nome
                primeiro = unidecode(self.df.loc[i][0][0]).lower()
                penultimo = unidecode(self.df.loc[i][0][-2]).lower()
                ultimo = unidecode(self.df.loc[i][0][-1]).lower()

                # Adiciona 1 ao contador de incidencia
                incidencia[ord(primeiro)-97][1] += 1
                incidencia[ord(penultimo)-97][2] += 1
                incidencia[ord(ultimo)-97][3] += 1

        dados = pd.DataFrame(incidencia, columns=["letra","primeiro","penultimo","ultimo"]) 
        return dados

    # Popula o vetor com os caracteres do alfabeto
    def popular_alfabeto(self, vetor):
        for i in range(97, 123):
            vetor[i - 97][0] = chr(i)

    # Retorna as vetores populados e tratados
    def gerar_dados(self):
        self.popular_alfabeto(self.incidencia_masc)
        self.popular_alfabeto(self.incidencia_fem)

        dados = [
            self.conta_dados('M', self.incidencia_masc),
            self.conta_dados('F', self.incidencia_fem)
        ]
        dados[0] = dados[0].replace([0],1)
        dados[1] = dados[1].replace([0],1)
        return dados
    
    # Pega o total do nomes por genero
    def pega_total(self, genero):
        total = 0
        for i in range(self.df.shape[0]):
            if self.df.loc[i][1] == genero:
                total += 1
        return total

    # Calcula o indice de sensibilidade de acordo com a incidencia
    def calculo_sensibilidade(self):
        dados = self.gerar_dados()

        columns = dados[0].columns
        for i in range(26):
            for j in range(1, len(columns)):
                self.df_masc[i][j-1] = round(
                    ( dados[0].loc[i][j] / dados[0][columns[j]].sum() ),
                    4
                )
                self.df_fem[i][j-1] = round(
                    ( dados[1].loc[i][j] / dados[1][columns[j]].sum() ),
                    4
                )

        total_masc = self.pega_total('M')
        total_fem = self.pega_total('F')
        probabilidadeMasc =  round(total_masc/(total_masc + total_fem),4)
        probabilidadeFem =  round(total_fem/(total_masc + total_fem),4)
        
        probabilidades = [probabilidadeMasc, probabilidadeFem]
        return probabilidades 

    # Função principal que faz a predicao do genero do nome
    def identifica_genero(self, nome):
        valores  = self.calculo_sensibilidade()

        # Pega a primeira, penultima e ultima letra do nome
        nome = unidecode(nome).lower()
        primeira_letra = nome[0]
        ultima_letra = nome[-1]
        penultima_letra = nome[-2]

        precisao_masculino = 0
        precisao_feminino  = 0

        # Pega o valor da letra na tabela de sensibilidade
        masculino_primeira_letra = self.df_masc[ord(primeira_letra)-97][0]
        feminino_primeira_letra =  self.df_fem[ord(primeira_letra)-97][0]
        masculino_penultima_letra = self.df_masc[ord(penultima_letra)-97][1]
        feminino_penultima_letra = self.df_fem[ord(penultima_letra)-97][1]
        masculino_ultima_letra = self.df_masc[ord(ultima_letra)-97][2]
        feminino_ultima_letra  =  self.df_fem[ord(ultima_letra)-97][2]

        # Calcula a precisao
        precisao_masculino = valores[0] * masculino_primeira_letra * masculino_penultima_letra * masculino_ultima_letra
        precisao_feminino  = valores[1] * feminino_primeira_letra * feminino_penultima_letra * feminino_ultima_letra
        precisao_masculino = round(math.log(precisao_masculino,10),4)
        precisao_feminino  = round(math.log(precisao_feminino,10),4)

        # return {
        #     "nome": nome,
        #     "predicao": "Masculino" if precisao_masculino > precisao_feminino else "Feminino",
        #     "precisao": {
        #         "masculino": precisao_masculino,
        #         "feminino": precisao_feminino
        #     }
        # }
        return "Masculino" if precisao_masculino > precisao_feminino else "Feminino"
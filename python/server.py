from flask import Flask, jsonify
from flask import request
from NaiveBayes import NaiveBayes

app = Flask(__name__)

# Rota principal que recebe os nomes para predicao
@app.route("/", methods=["GET"])
def main():
    # Pega os nomes vindos da URL
    nome = request.args.get('nome')
    nb = NaiveBayes()
    predicao = nb.identifica_genero(nome)
    return jsonify(predicao)

app.run(host="127.0.0.1", port=5000)
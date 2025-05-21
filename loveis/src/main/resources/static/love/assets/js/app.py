from flask import Flask, jsonify
import google.generativeai as genai
from PIL import Image

app = Flask(__name__)

# API 키 설정
genai.configure(api_key="AIzaSyDXzY2Yn3hdnRGV9cz7yBCVE4vPk5eJ7FI")

@app.route("/generate", methods=["GET"])
def generate():
    # 모델 선택
    model = genai.GenerativeModel(model_name="gemini-2.0-flash")

    # 이미지 열기 (PIL 이미지 객체로)
    image_path = "C:/factory_down/TP_image/SE-A437F981-6194-457E-AC55-E6EE96080247.jpg"
    image = Image.open(image_path)

    # 이미지 + 텍스트 프롬프트 전송
    response = model.generate_content([
        image,
        "사진에 대해 설명해줘"
    ])

    return jsonify({"result": response.text})

if __name__ == "__main__":
    app.run(port=5000, debug=True)

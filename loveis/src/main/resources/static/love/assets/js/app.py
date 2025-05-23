from flask import Flask, request, jsonify
import google.generativeai as genai
from PIL import Image
import os

app = Flask(__name__)

# API 키 설정
genai.configure(api_key="AIzaSyDXzY2Yn3hdnRGV9cz7yBCVE4vPk5eJ7FI")

@app.route("/generate", methods=["POST"])
def generate():
    if 'file' not in request.files:
        return jsonify({"result": "No file part in the request"}), 400
    
    file = request.files['file']
    if file.filename == '':
        return jsonify({"result": "No selected file"}), 400
    
    upload_folder = "C:/uploads"
    os.makedirs(upload_folder, exist_ok=True)
    file_path = os.path.join(upload_folder, file.filename)
    file.save(file_path)

    mbti = request.form.get('mbti')
    
    try:
        # PIL 이미지 열기
        image = Image.open(file_path)

        # AI 모델 생성
        model = genai.GenerativeModel(model_name="gemini-2.0-flash")

        # 이미지 + 텍스트 프롬프트 전송
        response = model.generate_content([
            image,
            mbti + "처럼말해줘! 사진의 사람은 나인데 내 오늘 패션 어때?"
        ])

        return jsonify({"result": response.text})

    except Exception as e:
        return jsonify({"result": f"Error processing image: {str(e)}"}), 500

if __name__ == "__main__":
    app.run(port=5000, debug=True)

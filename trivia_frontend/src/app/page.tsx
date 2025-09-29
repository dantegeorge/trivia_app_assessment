"use client";

import { useState } from "react";

interface Question {
  questionText: string;
  possibleAnswers: string[];
}

interface AnswerResponse {
  correct: boolean;
}

function decodeHtml(html: string) {
  const txt = document.createElement("textarea");
  txt.innerHTML = html;
  return txt.value;
}

export default function Page() {
  const [question, setQuestion] = useState<Question | null>(null);
  const [loading, setLoading] = useState(false);
  const [feedback, setFeedback] = useState<string | null>(null);

  const fetchQuestion = async () => {
    setLoading(true);
    setFeedback(null);
    try {
      const res = await fetch("api/questions");
      const data = await res.json();
      console.log("Fetched question:", data);
      setQuestion(data);
    } catch (err) {
      console.error(err);
      setQuestion(null);
    } finally {
      setLoading(false);
    }
  };

  const checkAnswer = async (answer: string) => {
    if (!question) return;
    try {
      const res = await fetch("/api/check-answers", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          question: question.questionText,
          chosenAnswer: answer,
        }),
      });
      const result: AnswerResponse = await res.json();
      setFeedback(result.correct ? "✅ Correct!" : "❌ Wrong!");
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <main className="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-6">
      <h1 className="text-4xl font-bold text-blue-600 mb-6">Trivia App</h1>

      <button
        onClick={fetchQuestion}
        disabled={loading}
        className="bg-blue-500 text-white px-6 py-3 rounded-lg mb-6 hover:bg-blue-600 disabled:bg-gray-400"
      >
        {loading ? "Loading..." : "Get Question"}
      </button>

      {question && (
        <div className="bg-white shadow-lg rounded-xl p-6 max-w-xl w-full">
          <h2 className="text-xl font-semibold mb-4">
            {decodeHtml(question.questionText)}
          </h2>
          <div className="space-y-3">
            {question?.possibleAnswers?.map((ans, i) => (
              <button
                key={i}
                onClick={() => checkAnswer(ans)}
                className="w-full bg-gray-200 hover:bg-gray-300 px-4 py-2 rounded-lg"
              >
                {decodeHtml(ans)}
              </button>
            ))}
          </div>
        </div>
      )}

      {feedback && <div className="mt-6 text-lg font-bold">{feedback}</div>}
    </main>
  );
}

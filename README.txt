
# 🧪 End-to-End Test Flow

This document describes the **complete functional testing workflow** for the **AI Homework Reviewer** application.

The goal of this test flow is to verify that the system can:

* accept homework images
* analyze them using AI
* identify mistakes
* display corrections directly on the homework image

---

# 1️⃣ Launch the App

### Steps

1. Install and open the application.

### Expected Result

* App launches without crash
* Home screen loads successfully

The screen should display:

* Homework **review/chat area**
* Input bar with:

  * 📷 Upload / Camera icon
  * 📝 Text input field
  * ➤ Send button

---

# 2️⃣ Upload Homework (Gallery)

### Steps

1. Tap **Upload / Camera icon**
2. Select **Gallery**
3. Choose a homework image

The system is designed to review **multiple subjects**, including:

* English question–answers
* Mathematics problems
* Science formulas
* General knowledge questions

### Expected Result

* Selected image appears in chat as **Homework Submitted**
* Loader appears:

```
Analyzing homework...
```

The AI analysis is performed using the multimodal capabilities of
Gemini.

---

# 3️⃣ Upload Homework (Camera)

### Steps

1. Tap **Upload icon**
2. Select **Take Photo**
3. Grant **Camera permission** if prompted
4. Capture a photo of homework

### Expected Result

* Captured image appears in the chat area
* Loader appears while the AI processes the image

---

# 4️⃣ AI Homework Review Response

### Expected Result

After AI analysis completes, the application displays a **Homework Review Card** containing:

* Homework image
* Numbered mistake markers
* Overall result summary

Example:

```
❌ Incorrect

1. spelling mistake
2. grammar mistake
```

Each detected mistake corresponds to a **marker placed directly on the homework image**.

---

# 5️⃣ Marker Overlay Validation

### Steps

Observe the homework image.

### Expected Result

* Red numbered markers appear on the image
* Marker numbers match the mistake list
* Marker positions align with the correct location on the homework

Markers are generated using **normalized coordinates returned by the AI system** and rendered using the Android **Canvas API**.

---

# 6️⃣ Image Preview

### Steps

1. Tap the homework image

### Expected Result

* Full-screen preview dialog opens
* Image fills the screen width
* Mistake markers remain visible

---

# 7️⃣ Zoom Feature

### Steps

1. Pinch to zoom
2. Pan around the image

### Expected Result

* Image zooms smoothly
* Marker overlays remain **correctly aligned**
* No marker displacement occurs during zoom or pan

---

# 8️⃣ Marker Information

### Steps

1. Review the mistake list displayed below the image

### Expected Result

Each mistake contains:

```
1. Comment about the error
Correct answer: ...
```

The explanation should help the student **understand and correct the mistake**.

---

# 9️⃣ Text Interaction

### Steps

1. Type a message in the input box
2. Press **Send**

### Expected Result

* Message appears in the conversation
* AI response appears after processing

This allows additional **interactive clarification or explanations**.

---

# 🔟 Student Self-Assessment Test

The application also functions as a **self-assessment tool for students**.

### Steps

1. Solve a homework question on paper
2. Take a photo using the app
3. Submit for review

### Expected Result

Students receive:

* instant feedback
* mistake detection
* correct answers

This allows students to **check their work before submitting homework to teachers**.

---

# 1️⃣1️⃣ Error Testing

## Camera Permission Denied

### Steps

* Deny camera permission when prompted.

### Expected Result

User sees a message:

```
Camera permission required
```

---

## No Internet Connection

### Steps

1. Turn off internet
2. Upload homework

### Expected Result

* Error message displayed
* Loader stops or retry option appears

---

# 1️⃣2️⃣ UI / UX Checks

Verify the following UI behaviors:

✔ buttons respond correctly
✔ scrolling works smoothly
✔ no layout overlap
✔ markers visible on all screen sizes
✔ preview dialog opens and closes correctly

---

# 1️⃣3️⃣ Performance Test

Upload different image sizes:

* Small image (~1MB)
* Large image (~8MB)

### Expected Result

* Image loads successfully
* AI response is generated
* UI remains responsive during processing

---

# ⭐ Demo Flow for Hackathon Judges (Important)

Use this **simple 30-second demonstration flow**:

1️⃣ Open the app
2️⃣ Tap **Take Photo**
3️⃣ Capture homework
4️⃣ Loader appears: *Analyzing homework…*
5️⃣ AI returns corrections
6️⃣ Red markers appear directly on the homework
7️⃣ Tap image → zoom preview

This creates a **“wow moment”** because judges can instantly see **mistakes detected directly on the paper**.

---

# 🏆 Optional Extra Test (Recommended)

Test a sentence containing multiple mistakes.

Example homework text:

```
She go to school yesterday.
```

### Expected Result

```
1. Tense error
Correct: went
```

Additional possible corrections:

```
Correct sentence: She went to school yesterday.
```

---

✅ This updated test flow now validates:

* **AI homework analysis**
* **multisubject detection**
* **marker coordinate system**
* **student self-assessment capability**
* **full UI interaction**


📖 Story Illustration (Experimental)

The storytelling module currently focuses on generating a structured five-page story experience for children.

Each page contains short, easy-to-read text designed for young learners.

Image generation for story illustrations was partially implemented during development. However, due to rate limits from external image generation APIs during the hackathon environment, automatic illustration generation has been temporarily disabled to ensure a stable demo experience.

Future versions will include:

AI-generated illustrations for each story page

consistent character design across slides

enhanced visual storytelling for children

Despite this limitation, the storytelling feature still demonstrates how generative AI can help encourage creativity, reading practice, and imagination for students.

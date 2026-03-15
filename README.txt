
# 🧪 End-to-End Test Flow

## 1️⃣ Launch the App

**Expected Result**

* App opens without crash
* Home screen shows:

  * chat area / review area
  * input bar with **upload icon, text field, send button**

---

# 2️⃣ Upload Homework (Gallery)

### Steps

1. Tap **Upload / Camera icon**
2. Choose **Gallery**
3. Select an image of homework(The system is designed to
review homework across multiple subjects, including English question–answers, mathematics problem solving, and science formulas.)

**Expected Result**

* Image appears in chat as **Homework submitted**
* Loader appears:
  `Analyzing homework...`

---

# 3️⃣ Upload Homework (Camera)

### Steps

1. Tap **Upload icon**
2. Select **Take Photo**
3. Grant **Camera permission** if prompted
4. Capture homework photo

**Expected Result**

* Captured image appears in chat
* Loader appears while AI processes image

---

# 4️⃣ AI Review Response

### Expected Result

After AI response arrives:

You should see a **Homework Review Card** containing:

* Homework image
* Numbered mistake markers
* Overall result

Example:

```
❌ Incorrect
1. spelling mistake
2. grammar mistake
```

---

# 5️⃣ Marker Overlay Validation

### Steps

Observe the homework image.

**Expected Result**

* Red numbered markers appear
* Markers match mistake list numbers
* Marker positions align with text area

---

# 6️⃣ Image Preview

### Steps

1. Tap the homework image

**Expected Result**

* Full screen preview dialog opens
* Image fills width
* Markers visible on preview

---

# 7️⃣ Zoom Feature

### Steps

1. Pinch to zoom
2. Pan around image

**Expected Result**

* Image zooms smoothly
* Markers remain aligned with image

---

# 8️⃣ Marker Information

### Steps

1. Read mistake list below image

**Expected Result**
Each mistake shows:

```
1. Comment about error
Correct answer: ...
```

---

# 9️⃣ Text Interaction

### Steps

1. Type a message in text box
2. Press **Send**

**Expected Result**

* Message appears in chat
* AI response appears

---

# 🔟 Error Testing

Test these cases:

### Camera Permission Denied

Steps:

* Deny camera permission

Expected:

```
Camera permission required
```

---

### No Internet

Steps:

* Turn off internet
* Upload homework

Expected:

* Error message or loader stops

---

# 1️⃣1️⃣ UI / UX Checks

Check:

✔ buttons clickable
✔ scrolling works
✔ no layout overlap
✔ markers visible on all screen sizes
✔ dialog closes correctly

---

# 1️⃣2️⃣ Performance Test

Upload:

* small image (~1MB)
* large image (~8MB)

Expected:

* image loads
* AI response appears
* UI remains responsive

---

# ⭐ Demo Flow for Hackathon Judges (Important)

Use this **simple 30-second flow**:

1️⃣ Open app
2️⃣ Tap **Take Photo**
3️⃣ Capture homework
4️⃣ Show loader
5️⃣ AI returns corrections
6️⃣ Tap image → zoom preview

This creates a **wow moment** because judges see **mistakes appear directly on the paper**.

---

# 🏆 Optional Extra Test (Very Good)

Test multiple mistakes:

Example image containing:

```
She go to school yesterday.
```

Expected:

```
1. Tense error
Correct: went
```


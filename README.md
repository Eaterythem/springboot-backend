
# EateryThem

**EateryThem** is a backend service that helps users answer a simple daily question:  
**"What should I eat tomorrow?"**

Rather than making random decisions every day, EateryThem lets users **build, track, and share meal plans** through reusable recipe cycles — turning meal planning into a structured, shareable, and stress-free habit.

---

## 🧠 Core Concepts

### 1. **Meal Plans**
Each user can have ongoing meal plans, which define what they will eat each day. A meal plan:
- Starts from a given date.
- Covers **breakfast**, **lunch**, and **dinner**.
- Pulls recipes from **Meal Cycles** (see below).
- Automatically rotates through recipes day by day.

---

### 2. **Meal Cycles**
A **meal cycle** is a user-created sequence of recipes intended for a specific meal type (e.g., a breakfast cycle).  
It defines the order in which meals should repeat over time — like a playlist for your meals. For example:
- "Office Lunches" → [Chicken Wrap → Lentil Salad → Pasta Box]
- "Quick Dinners" → [Omelette → Tuna Sandwich → Stir Fry]

Cycles can be:
- **Private** (for personal use)
- **Public** (shareable with others)

---

### 3. **Meal Entries**
Each day in a plan is broken down into **meal entries**:
- Tracks the **planned** and the **actual** recipe eaten.
- Stores notes and meal status (e.g., skipped, completed).
- Allows flexibility to adjust what was actually consumed.

---

### 4. **Recipes**
Recipes are user-generated and contain:
- A name, instructions, ingredients, and tags.
- Belong to a user, but can be reused in multiple cycles.

---

### 5. **Community Sharing**
Users can:
- Share meal cycles with others.
- Browse public cycles to get inspiration.
- Mix and match community-created cycles with their own.

---

## 🧭 Example Flow

1. **User A** creates a "Healthy Lunch" cycle with 5 recipes.
2. User A starts a new meal plan for next week using that cycle.
3. Each day, their lunch is auto-filled from the cycle (rotating order).
4. User A logs what they actually ate and makes notes.
5. **User B** finds and adopts the "Healthy Lunch" cycle from User A.

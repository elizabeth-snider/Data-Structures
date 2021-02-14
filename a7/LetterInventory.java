        /* Student information for assignment:
         *
         *  On my honor, Elizabeth, this programming assignment is my own work
         *  and I have not provided this code to any other student.
         *
         *  UTEID: eys275
         *  email address: elizabethsnider2011@gmail.com
         *  TA name: Henry Liu
         *  Number of slip days I am using: 1
         */

        public class LetterInventory implements Comparable<LetterInventory> {

            public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";
            public static final int TOTAL_LETTERS = 26;
            public static final char A = 'a';
            public static final char Z = 'z';
            public int[] freq;
            public int size;

            //constructor for LetterInventory that creates an int array based on the letters
            //in a string
            //pre: String cannot be null
            //@param accepts a string
            LetterInventory(String word){
                if(word == null){
                    throw new IllegalArgumentException("word cannot be null.");
                }

                word = word.toLowerCase();
                freq = new int[TOTAL_LETTERS];
                size = 0;

                for(int i = 0; i < word.length(); i++){
                    if(word.charAt(i) >= A && word.charAt(i) <= Z){
                        freq[word.charAt(i) - A]++;
                        size++;
                    }
                }
            }

            //returns frequency of the @param passed char
            //pre: requires that the char be an English letter
            public int get(char letter){
                String lower = letter + "";
                lower = lower.toLowerCase();
                char lowerLetter = lower.charAt(0);

                if(!alphabet.contains(lower)){
                    throw new IllegalArgumentException("letter has to be in the English alphabet");
                }

                int index = alphabet.indexOf(lowerLetter);
                return freq[index];
            }

            //returns the total number of letters in the current LetterInventory
            public int size(){
                return size;
            }

            //returns true if the size of the current LetterInventory is zero
            //and false otherwise
            public boolean isEmpty(){
                return size == 0;
            }

            //returns a String representation of the current LetterInventory
            //letters are listed in alphabetical order
            public String toString(){
                StringBuilder result = new StringBuilder();

                for(int i = 0; i < freq.length; i++){
                    if(freq[i] != 0) {
                        for (int j = freq[i]; j > 0; j--) {
                            result.append(alphabet.charAt(i));
                        }
                    }
                }

                return result.toString();
            }

            //returns a new LetterInventory created by adding the frequencies from the
            //@param passed LetterInventory object to the current LetterInventory
            //pre: passed LetterInventory object cannot be null
            public LetterInventory add(LetterInventory other){
                if(other == null){
                    throw new IllegalArgumentException("object cannot be null");
                }

                LetterInventory result = new LetterInventory("");

                for(int i = 0; i < TOTAL_LETTERS; i++){
                    result.freq[i] = this.freq[i] + other.get(alphabet.charAt(i));
                }

                result.size = this.size + other.size;
                return result;
            }

            //returns a new LetterInventory object created by subtracting the letter frequencies
            //of the @param passed LetterInventory object from the current LetterInventory
            //pre: passed LetterInventory object cannot be null
            public LetterInventory subtract(LetterInventory other){
                if(other == null){
                    throw new IllegalArgumentException("object cannot be null");
                }

                LetterInventory result = new LetterInventory("");

                for(int i = 0; i < TOTAL_LETTERS; i++){
                    result.freq[i] = this.freq[i] - other.freq[i];

                    if(result.freq[i] < 0){
                        return null;
                    }
                }

                result.size = this.size - other.size;
                return result;
            }

            //returns true if the @param passed Object contains the same letter frequencies
            //as the current LetterInventory
            public boolean equals(Object obj){
                if(obj instanceof LetterInventory) {
                    LetterInventory other = (LetterInventory)obj;
                    return this.toString().compareTo(other.toString()) == 0;
                } else {
                    return false;
                }
            }

            //Comparator constructor that allows the comparison of two LetterInventory objects
            public int compareTo(LetterInventory other) {
                return this.toString().compareTo(other.toString());
            }
        }


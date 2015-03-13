import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

 
/**
 * Encrypts and Decrypts text using the Caesar Cihper algorithm.
 * @author Invisible Computer, JTN
 *
 */
public class CaesarGUI extends JFrame implements ActionListener {
 
        private static final long serialVersionUID = 1L;
        private static String alphabet = "abcdefghijklmnopqrstuvwxyz ";
        private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        private static String symbols  = "!@#$%^&*()_+-=<>?,./[]{}|' ";
        private static String numbers  = "0123456789"; 
        private JTextField shiftFactor;
        private JTextArea inputTA;
        private JTextArea outputTA;
 
 
        /**
         * @param args
         */
        public static void main(String[] args) {
                new CaesarGUI().setVisible(true);
        }
       
        public void encryptText() throws InterruptedException {
                //Create a HashMap
                //A hash map takes keys and values, which are both Characters in this case.
                HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
                int shift;
                //Get the text from the app and store it in a String variable.
                String textNum = this.shiftFactor.getText();
                //Check to see if a "Shift Factor" value was entered.
                //If there wasn't, set shift to zero,
                //Otherwise parse the input value to an integer so we can use it.
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%26;
                }
                else{
                        shift = 0;
                }
                //Map every letter of the alphabet to another letter in the alphabet, shifted by x places.
                for(int i=0; i<symbols.length(); i++){
                        alphaMap.put(alphabet.charAt(i), alphabet.charAt((i+shift)%26));
                        alphaMap.put(ALPHABET.charAt(i), ALPHABET.charAt((i+shift)%26));
                        alphaMap.put(symbols.charAt(i), symbols.charAt((i+shift)%26));
                        
                }
                alphaMap.put(alphabet.charAt(alphabet.length() - 1), ' ');
                
                for(int i=0; i<numbers.length(); i++){
                     alphaMap.put(numbers.charAt(i), numbers.charAt((i+shift)%10));
                }
               

                
                //Get input text 
                String inputText = inputTA.getText();
                String outputText = "";
                //Go to each letter and switch it with it's shifted counterpart
                for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }
                //Output the encrypted text
                outputTA.setText(outputText);
        }
       
        public void decryptText() throws InterruptedException{
                HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
                int shift;
                String textNum = this.shiftFactor.getText();
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%26;
                }
                else{
                        shift = 0;
                }
                for(int i=0; i<symbols.length()-1; i++){
                        alphaMap.put(alphabet.charAt((i+shift)%26), alphabet.charAt(i));
                        alphaMap.put(ALPHABET.charAt((i+shift)%26), ALPHABET.charAt(i));
                        alphaMap.put(symbols.charAt((i+shift)%26), symbols.charAt(i));
                   
                }
                alphaMap.put(' ', alphabet.charAt(alphabet.length() - 1));
                
                for(int i=0; i<numbers.length(); i++){
                     alphaMap.put(numbers.charAt((i+shift)%10), numbers.charAt(i));
                }

                
                String inputText = inputTA.getText();
                String outputText = "";
                for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }
                outputTA.setText(outputText);
        }
        
        public void clearText() throws InterruptedException{
                  String inputText = "";
                  inputTA.setText(inputText);
                  String outputText = "";
                  outputTA.setText(outputText);
        }
       
        public CaesarGUI(){
                setTitle("Caesar Cipher");
            setVisible(true);
            setDefaultCloseOperation(3);
 
            Container content = getContentPane();
            GridLayout layout = new GridLayout(4, 0, 0, 10);
            content.setLayout(layout);
 
            inputTA = new JTextArea("Type your message into this box.", 6, 40);
            inputTA.setLineWrap(true);
            inputTA.setWrapStyleWord(true);
            inputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller = new JScrollPane(inputTA);
            scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller);
           
            JPanel box1 = new JPanel();
            box1.setLayout(new FlowLayout());
            box1.add(new JLabel("Shift Factor"));
            box1.add(this.shiftFactor = new JTextField(7));
            content.add(box1);
           
            

            outputTA = new JTextArea("Your message will then be either encrypted or decrypted and displayed in this box.", 6, 40);
            outputTA.setLineWrap(true);
            outputTA.setWrapStyleWord(true);
            outputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller2 = new JScrollPane(outputTA);
            scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller2);
          
            JPanel box2 = new JPanel();
            box2.setLayout(new FlowLayout());
            JButton decryptButton = new JButton("Decrypt");
            JButton encryptButton = new JButton("Encrypt");
            JButton clearButton   = new JButton("Clear Text");
            clearButton.addActionListener(this);
            decryptButton.addActionListener(this);
            encryptButton.addActionListener(this);
            box2.add(decryptButton);
            box2.add(encryptButton);
            box2.add(clearButton);
            content.add(box2);

          
          
            pack();
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("Encrypt")){
                        try{
                                encryptText();
                        }
                        catch(InterruptedException e1){
                                e1.printStackTrace();
                        }
                }
                if (e.getActionCommand().equals("Decrypt"))
                      try {
                        decryptText();
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
                if (e.getActionCommand().equals("Clear Text"))
                      try {
                        clearText();
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
        }
        
        



}
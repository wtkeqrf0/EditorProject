import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

// чтобы сохранить настройки шрифта необходимо закрыть окно
public class SetFont extends JFrame {
    private final JSpinner range = new JSpinner();
    JRadioButton c1 = new JRadioButton("Обычный"), c2 = new JRadioButton("Полужирный");
    Font fac = new Font("Times New Roman", Font.PLAIN, 20), foc = new Font("Times New Roman", Font.PLAIN, 14);

    SetFont(JTextArea j) {
        //настройка окна
        super("Шрифт");
        Font fon = j.getFont();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 40, 30));

//создание всех переменных
        JSpinner font = new JSpinner(new SpinnerListModel(List.of("Times New Roman", "Impact", "Arial", "Calibri")));
        JLabel text1 = new JLabel("Размер шрифта"), text2 = new JLabel("Шрифт");
        Font fac = new Font("Times New Roman", Font.PLAIN, 20), foc = new Font("Times New Roman", Font.PLAIN, 14);
        JRadioButton c1 = new JRadioButton("Обычный"), c2 = new JRadioButton("Полужирный");
//установить шрифт для объектов
        c1.setFont(foc);
        c2.setFont(foc);
        text1.setFont(foc);
        text2.setFont(foc);
        range.setFont(fac);
        ButtonGroup bg = new ButtonGroup();
        bg.add(c1);
        bg.add(c2);
//присваиваем старые значения шрифта
        range.setValue(fon.getSize());
        if (fon.getStyle() == Font.BOLD) c2.setSelected(true);
        else c1.setSelected(true);

//если окно закрывается, то нужно сохранить настройки шрифта
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                j.setFont(new Font((String) font.getValue(),
                        c2.isEnabled() ? Font.BOLD : Font.PLAIN, (Integer) range.getValue()));
            }
        });

//добавление объектов в окно
        add(text1);
        add(range);
        add(c1);
        add(c2);
        add(text2);
        add(font);
        setVisible(true);
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class Editor extends JFrame implements ActionListener {
    //создание области для записи текста
    JTextArea t = new JTextArea();

    // Запуск программы
    Editor() {
// Называем окно
        super("Editor");

// Создание шрифта текста
        t.setFont(new Font("Times New Roman", Font.PLAIN, 20));

// Создаём строку меню
        JMenuBar mb = new JMenuBar();

// Создать подменю для меню
        JMenu m1 = new JMenu("Файл");
// Создание подпунктов меню
        JMenuItem mi1 = new JMenuItem("Убрать всё");
        JMenuItem mi2 = new JMenuItem("Открыть");
        JMenuItem mi3 = new JMenuItem("Сохранить");
        JMenuItem mi9 = new JMenuItem("Печать");
// Добавить действия
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);
// Создать подменю для меню
        JMenu m2 = new JMenu("Текст");
// Создание подпунктов меню
        JMenuItem mi4 = new JMenuItem("обрезать");
        JMenuItem mi5 = new JMenuItem("копировать");
        JMenuItem mi6 = new JMenuItem("вставить");
        JMenuItem mi7 = new JMenuItem("шрифт");
// Добавить действия
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        m2.add(mi7);
        JMenuItem mc = new JMenuItem("Закрыть");
        mc.addActionListener(this);
        mb.add(m1);
        mb.add(m2);
        mb.add(mc);
        setJMenuBar(mb);
        add(new JScrollPane(t));
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Если кнопка нажата
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case "обрезать" -> t.cut();
            case "копировать" -> t.copy();
            case "вставить" -> t.paste();
//создаёт новый класс (смотри в классе SetFont)
            case "шрифт" -> new SetFont(t);
            case "Сохранить" -> {
// Создать объект класса JFileChooser
                JFileChooser j = new JFileChooser("f:");
// Вызвать функцию ShowSaveDialog, чтобы показать диалог сохранения
                int r = j.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
// Установить метку на путь к выбранному каталогу
                    File fi = new File(j.getSelectedFile().getAbsolutePath());
                    try {
// Создать файл писателя
                        FileWriter wr = new FileWriter(fi, false);
// Создать буферизованный писатель для записи
                        BufferedWriter w = new BufferedWriter(wr);
// Написать
                        w.write(t.getText());
                        w.flush();
                        w.close();
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(this, "Ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
// Если пользователь отменил операцию
                else JOptionPane.showMessageDialog(this, "Операция отменена", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            case "Печать" -> {
                try {
// распечатать файл
                    t.print();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, "Ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Открыть" -> {
// Создать объект класса JFileChooser
                JFileChooser j = new JFileChooser("f:");
// Вызвать функцию showsOpenDialog, чтобы показать диалоговое окно сохранения
                int r = j.showOpenDialog(null);
// Если пользователь выбирает файл
                if (r == JFileChooser.APPROVE_OPTION) {
// Установить метку на путь к выбранному каталогу
                    File fi = new File(j.getSelectedFile().getAbsolutePath());
                    try {
// Строка
                        String s1;
                        StringBuilder sl;
// Чтение файла
                        FileReader fr = new FileReader(fi);
// Буферный ридер
                        BufferedReader br = new BufferedReader(fr);
// Инициализируем sl
                        sl = new StringBuilder(br.readLine());
// Взять ввод из файла
                        t.setText(sl.toString());
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(this, "Ошибка", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
// Если пользователь отменил операцию
                else JOptionPane.showMessageDialog(this, "Операция отменена", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            case "Убрать всё" -> t.setText("");
            case "Закрыть" -> System.exit(0);
        }
    }

    // Начало программы
    public static void main(String[] args) {
        new Editor();
    }
}

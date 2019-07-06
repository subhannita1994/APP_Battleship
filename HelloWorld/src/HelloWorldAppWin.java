import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Color;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class HelloWorldAppWin {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HelloWorldAppWin window = new HelloWorldAppWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(532, 306);
		
		Group grpPlayer_1 = new Group(shell, SWT.NONE);
		grpPlayer_1.setText("Player 1");
		grpPlayer_1.setBounds(39, 24, 404, 272);

		Button b2 = new Button(grpPlayer_1, SWT.TOGGLE);
		b2.setBounds(100, 0, 94, 27);
		b2.setText("0,1");
		
		Button b1 = new Button(grpPlayer_1, SWT.TOGGLE);
		b1.setBounds(0, 0, 94, 27);
		b1.setText("0,0");
		
		Button b6 = new Button(grpPlayer_1, SWT.TOGGLE);
		b6.setBounds(100, 33, 94, 27);
		b6.setText("1,1");
		
		Button b5 = new Button(grpPlayer_1, SWT.TOGGLE);
		b5.setBounds(0, 33, 94, 27);
		b5.setText("1,0");
		
		Button b3 = new Button(grpPlayer_1, SWT.TOGGLE);
		b3.setText("0,2");
		b3.setBounds(200, 0, 94, 27);
		
		Button b4 = new Button(grpPlayer_1, SWT.TOGGLE);
		b4.setText("0,3");
		b4.setBounds(300, 0, 94, 27);
		
		Button b7 = new Button(grpPlayer_1, SWT.TOGGLE);
		b7.setText("1,2");
		b7.setBounds(200, 33, 94, 27);
		
		Button b8 = new Button(grpPlayer_1, SWT.TOGGLE);
		b8.setText("1,3");
		b8.setBounds(300, 33, 94, 27);
		
		Button b9 = new Button(grpPlayer_1, SWT.TOGGLE);
		b9.setText("2,0");
		b9.setBounds(0, 66, 94, 27);
		
		Button b10 = new Button(grpPlayer_1, SWT.TOGGLE);
		b10.setText("3,0");
		b10.setBounds(0, 99, 94, 27);
		
		Button b11 = new Button(grpPlayer_1, SWT.TOGGLE);
		b11.setText("3,1");
		b11.setBounds(100, 99, 94, 27);
		
		Button b12 = new Button(grpPlayer_1, SWT.TOGGLE);
		b12.setText("2,1");
		b12.setBounds(100, 66, 94, 27);
		
		Button b13 = new Button(grpPlayer_1, SWT.TOGGLE);
		b13.setText("2,2");
		b13.setBounds(200, 66, 94, 27);
		
		Button b14 = new Button(grpPlayer_1, SWT.TOGGLE);
		b14.setText("3,2");
		b14.setBounds(200, 99, 94, 27);
		
		Button b15 = new Button(grpPlayer_1, SWT.TOGGLE);
		b15.setText("3,3");
		b15.setBounds(300, 99, 94, 27);
		
		Button b16 = new Button(grpPlayer_1, SWT.TOGGLE);
		b16.setText("2,3");
		b16.setBounds(300, 66, 94, 27);
		
		Button b17 = new Button(grpPlayer_1, SWT.TOGGLE);
		b17.setText("4,0");
		b17.setBounds(0, 132, 94, 27);
		
		Button b18 = new Button(grpPlayer_1, SWT.TOGGLE);
		b18.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				// TODO Auto-generated method stub
				b15.setGrayed(true);
			}
			
		});
		b18.setText("5,0");
		b18.setBounds(0, 165, 94, 27);
		
		Button b19 = new Button(grpPlayer_1, SWT.TOGGLE);
		b19.setText("5,1");
		b19.setBounds(100, 165, 94, 27);
		
		Button b20 = new Button(grpPlayer_1, SWT.TOGGLE);
		b20.setText("4,1");
		b20.setBounds(100, 132, 94, 27);
		
		Button b21 = new Button(grpPlayer_1, SWT.TOGGLE);
		b21.setText("4,2");
		b21.setBounds(200, 132, 94, 27);
		
		Button b22 = new Button(grpPlayer_1, SWT.TOGGLE);
		b22.setText("5,2");
		b22.setBounds(200, 165, 94, 27);
		
		Button b23 = new Button(grpPlayer_1, SWT.TOGGLE);
		b23.setText("5,3");
		b23.setBounds(300, 165, 94, 27);
		
		Button b24 = new Button(grpPlayer_1, SWT.TOGGLE);
		b24.setText("4,3");
		b24.setBounds(300, 132, 94, 27);
		
		/*
		Group grpPlayer = new Group(shell, SWT.NONE);
		grpPlayer.setText("Player 2");
		grpPlayer.setBounds(39, 324, 404, 272);
		
		Button button_1 = new Button(grpPlayer, SWT.NONE);
		button_1.setText("0,1");
		button_1.setBounds(100, 0, 94, 27);
		
		Button button_2 = new Button(grpPlayer, SWT.NONE);
		button_2.setText("0,0");
		button_2.setBounds(0, 0, 94, 27);
		
		Button button_3 = new Button(grpPlayer, SWT.NONE);
		button_3.setText("1,1");
		button_3.setBounds(100, 33, 94, 27);
		
		Button button_4 = new Button(grpPlayer, SWT.NONE);
		button_4.setText("1,0");
		button_4.setBounds(0, 33, 94, 27);
		
		Button button_25 = new Button(grpPlayer, SWT.NONE);
		button_25.setText("0,2");
		button_25.setBounds(200, 0, 94, 27);
		
		Button button_26 = new Button(grpPlayer, SWT.NONE);
		button_26.setText("0,3");
		button_26.setBounds(300, 0, 94, 27);
		
		Button button_27 = new Button(grpPlayer, SWT.NONE);
		button_27.setText("1,2");
		button_27.setBounds(200, 33, 94, 27);
		
		Button button_28 = new Button(grpPlayer, SWT.NONE);
		button_28.setText("1,3");
		button_28.setBounds(300, 33, 94, 27);
		
		Button button_29 = new Button(grpPlayer, SWT.NONE);
		button_29.setText("2,0");
		button_29.setBounds(0, 66, 94, 27);
		
		Button button_30 = new Button(grpPlayer, SWT.NONE);
		button_30.setText("3,0");
		button_30.setBounds(0, 99, 94, 27);
		
		Button button_31 = new Button(grpPlayer, SWT.NONE);
		button_31.setText("3,1");
		button_31.setBounds(100, 99, 94, 27);
		
		Button button_32 = new Button(grpPlayer, SWT.NONE);
		button_32.setText("2,1");
		button_32.setBounds(100, 66, 94, 27);
		
		Button button_33 = new Button(grpPlayer, SWT.NONE);
		button_33.setText("2,2");
		button_33.setBounds(200, 66, 94, 27);
		
		Button button_34 = new Button(grpPlayer, SWT.NONE);
		button_34.setText("3,2");
		button_34.setBounds(200, 99, 94, 27);
		
		Button button_35 = new Button(grpPlayer, SWT.NONE);
		button_35.setText("3,3");
		button_35.setBounds(300, 99, 94, 27);
		
		Button button_36 = new Button(grpPlayer, SWT.NONE);
		button_36.setText("2,3");
		button_36.setBounds(300, 66, 94, 27);
		
		Button button_37 = new Button(grpPlayer, SWT.NONE);
		button_37.setText("4,0");
		button_37.setBounds(0, 132, 94, 27);
		
		Button button_38 = new Button(grpPlayer, SWT.NONE);
		button_38.setText("5,0");
		button_38.setBounds(0, 165, 94, 27);
		
		Button button_39 = new Button(grpPlayer, SWT.NONE);
		button_39.setText("5,1");
		button_39.setBounds(100, 165, 94, 27);
		
		Button button_40 = new Button(grpPlayer, SWT.NONE);
		button_40.setText("4,1");
		button_40.setBounds(100, 132, 94, 27);
		
		Button button_41 = new Button(grpPlayer, SWT.NONE);
		button_41.setText("4,2");
		button_41.setBounds(200, 132, 94, 27);
		
		Button button_42 = new Button(grpPlayer, SWT.NONE);
		button_42.setText("5,2");
		button_42.setBounds(200, 165, 94, 27);
		
		Button button_43 = new Button(grpPlayer, SWT.NONE);
		button_43.setText("5,3");
		button_43.setBounds(300, 165, 94, 27);
		
		Button button_44 = new Button(grpPlayer, SWT.NONE);
		button_44.setText("4,3");
		button_44.setBounds(300, 132, 94, 27);
		*/

	}
}

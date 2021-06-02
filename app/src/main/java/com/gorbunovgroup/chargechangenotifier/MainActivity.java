package com.gorbunovgroup.chargechangenotifier;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import androidx.core.content.ContextCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.net.Uri;
import android.media.RingtoneManager;
import android.widget.Switch;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Vibrator;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private AdView mAdView;
    static Context mContext;
    static boolean status;
    public static final String CHANNEL_ID = "NotofiersChannel";
    Button btnStartService, btnStopService, notifyButton, settingsText, settingsImage;
    Switch StartSwitch, EndSwitch;

    ImageButton en,ch,ru,es,ge,fr, info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mContext = getApplicationContext();
        createNotificationChannel();
        btnStartService = findViewById(R.id.buttonStartService);
        btnStopService = findViewById(R.id.buttonStopService);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });
        //notifyButton = findViewById(R.id.notify);
        //notifyButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        sendNotify();
        //    }
        //});

        status = IsCharge();
        StartSwitch = findViewById(R.id.StartNotif);
        EndSwitch = findViewById(R.id.EndNotif);

        en = findViewById(R.id.english);
        en.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AppModel.CountryCode="EN";
                System.out.println(AppModel.CountryCode);
                UpdateLanguage();
            }
        });

        ru = findViewById(R.id.russia);
        ru.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AppModel.CountryCode="RU";
                System.out.println(AppModel.CountryCode);
                UpdateLanguage();
            }
        });

        ch = findViewById(R.id.china);
        ch.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AppModel.CountryCode="CH";
                System.out.println(AppModel.CountryCode);
                UpdateLanguage();
            }
        });

        es = findViewById(R.id.spain);
        es.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AppModel.CountryCode="ES";
                System.out.println(AppModel.CountryCode);
                UpdateLanguage();
            }
        });

        ge = findViewById(R.id.germany);
        ge.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AppModel.CountryCode="GE";
                System.out.println(AppModel.CountryCode);
                UpdateLanguage();
            }
        });

        fr = findViewById(R.id.france);
        fr.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AppModel.CountryCode="FR";
                System.out.println(AppModel.CountryCode);
                UpdateLanguage();
            }
        });
        UpdateLanguage();

        settingsText = findViewById(R.id.settingsText);
        settingsText.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);

            }
        });



        info = findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                switch (AppModel.CountryCode){
                    case "EN":
                        builder.setTitle("About & Privacy Policy");
                        builder.setMessage("Charge Change Notifier app helps you charge your device!\n" +
                                "\n" +
                                "If your phone or tablet suddenly disconnects from the wall outlet or the cable breaks and charging stops, you will immediately receive sound_5 about it. You can also turn on the sound_5 when the device starts charging!\n" +
                                "\n" +
                                "Current version - 1.0\n" +
                                "\n" +
                                "What is planned in the next update?\n" +
                                "\n" +
                                "- Notification of a critically low charge level\n" +
                                "- Notification of a sufficient charge level\n" +
                                "- List of ringtones to choose from\n" +
                                "\n" +
                                "Thank you for using the Charge Change Notifier app!\n\n" +
                                "Privacy Policy\n" +
                                "\n" +
                                "1. The term “personal information \" as used herein is defined as any information that identifies or can be used to identify, communicate with, or search for the person to whom such information relates. The personal information we collect will be subject to this privacy policy, as amended from time to time.\n" +
                                "\n" +
                                "2. We do not request your email address, phone number, or any information about you.\n" +
                                "\n" +
                                "3. We do not sell content.\n" +
                                "\n" +
                                "4. We do not require registration.\n" +
                                "\n" +
                                "5. The security of your personal information will not be compromised.\n" +
                                "\n" +
                                "6. We do not ask for location information through our mobile app.\n" +
                                "\n" +
                                "7. We do not send email newsletters.\n" +
                                "\n" +
                                "Required permissions\n" +
                                "\n" +
                                "1. Access to notifications and vibration feedback is used to notify the user of a change in the state of charge of the phone.\n" +
                                "\n" +
                                "2. Internet access is used for the AdMob service.\n" +
                                "\n" +
                                "3. Preventing the device from going to sleep mode is necessary for the correct operation of the application\n" +
                                "\n" +
                                "Contacts\n" +
                                "e-mail: gorbunovgroup@mail.ru");
                        break;
                    case "CH":
                        builder.setTitle("关于&隐私政策");
                        builder.setMessage("充电更改通知应用程序可以帮助您为您的设备充电！\n" +
                                "\n" +
                                "如果您的手机或平板电脑突然从墙上插座断开或电缆断裂和充电停止，您将立即收到通知。 您也可以在设备开始充电时打开通知！\n" +
                                "\n" +
                                "当前版本-1.0\n" +
                                "\n" +
                                "什么是计划在未来的更新？\n" +
                                "\n" +
                                "-极低收费水平的通知\n" +
                                "-通知足够的收费水平\n" +
                                "-铃声列表可供选择\n" +
                                "\n" +
                                "感谢您使用收费更改通知应用程序！\n\n" +
                                "隐私政策\n" +
                                "\n" +
                                "1. 本文中使用的\"个人信息\"一词被定义为识别或可用于识别、与之通信或搜索此类信息相关人员的任何信息。 我们收集的个人信息将受本隐私政策的约束，该政策不时修订。\n" +
                                "\n" +
                                "2. 我们不要求您的电子邮件地址，电话号码或任何关于您的信息。\n" +
                                "\n" +
                                "3. 我们不出售内容。\n" +
                                "\n" +
                                "4. 我们不需要注册。\n" +
                                "\n" +
                                "5. 您的个人信息的安全性不会受到损害。\n" +
                                "\n" +
                                "6. 我们不要求通过我们的移动应用程序的位置信息。\n" +
                                "\n" +
                                "7. 我们不发送电子邮件通讯。\n" +
                                "\n" +
                                "所需权限\n" +
                                "\n" +
                                "1. 访问通知和振动反馈用于通知用户手机充电状态的变化。\n" +
                                "\n" +
                                "2. 互联网接入用于AdMob服务。\n" +
                                "\n" +
                                "3. 防止设备进入睡眠模式是必要的应用程序的正确操作\n" +
                                "\n" +
                                "联系方式\n" +
                                "e-mail: gorbunovgroup@mail.ru");
                        break;
                    case "RU":
                        builder.setTitle("О приложении и политика конфиденциальности");
                        builder.setMessage("Приложение Charge Change Notifier поможет вам зарядить ваше устройство!\n" +
                                "\n" +
                                "Если ваш телефон или планшет внезапно отключится от розетки или кабель оборвется и зарядка прекратится, вы немедленно получите уведомление об этом. Вы также можете включить уведомление, когда устройство начнет заряжаться!\n" +
                                "\n" +
                                "Текущая версия - 1.0\n" +
                                "\n" +
                                "Что планируется в следующем обновлении?\n" +
                                "\n" +
                                "- Уведомление о критически низком уровне заряда\n" +
                                "- Уведомление о достаточном уровне заряда\n" +
                                "- Список мелодий на выбор\n" +
                                "\n" +
                                "Благодарим вас за использование приложения Charge Change Notifier!\n\n" +
                                "Политика конфиденциальности\n" +
                                "\n" +
                                "1. Термин “личная информация”, используемый в настоящем документе, определяется как любая информация, которая идентифицирует или может использоваться для идентификации, связи или поиска человека, к которому такая информация относится. Личная информация, которую мы собираем, будет являться предметом настоящей политики конфиденциальности, с вносимыми время от времени поправками.\n" +
                                "\n" +
                                "2. Мы не запрашиваем Ваш адрес электронной почты, телефон или какую нибудь информацию о Вас.\n" +
                                "\n" +
                                "3. Мы не продаем контент.\n" +
                                "\n" +
                                "4. Мы не требуем регистрации.\n" +
                                "\n" +
                                "5. Безопасность Вашей персональной информации не будет нарушена.\n" +
                                "\n" +
                                "6. Мы не просим информацию о местоположении через наше мобильное приложение.\n" +
                                "\n" +
                                "7. Мы не отправляем на электронную почту рассылку новостей.\n" +
                                "\n" +
                                "Требуемые разрешения\n" +
                                "\n" +
                                "1. Доступ к уведомлениям и виброотклику используется для уведомления пользователя о смене состояния заряда телефона.\n" +
                                "\n" +
                                "2. Доступ к интернету используется для работы сервиса AdMob.\n" +
                                "\n" +
                                "3. Предотвращение перехода девайса в спящий режим необходимо для корректной работы приложения\n" +
                                "\n" +
                                "Контакты\n" +
                                "e-mail: gorbunovgroup@mail.ru");
                        break;
                    case "ES":
                        builder.setTitle("Acerca De & Política De Privacidad");
                        builder.setMessage("La aplicación Charge Change Notifier le ayuda a cargar su dispositivo!\n" +
                                "\n" +
                                "Si su teléfono o tableta se desconecta repentinamente de la toma de corriente de la pared o el cable se rompe y la carga se detiene, recibirá inmediatamente una notificación al respecto. También puede activar la notificación cuando el dispositivo comienza a cargar!\n" +
                                "\n" +
                                "Versión actual 1.0\n" +
                                "\n" +
                                "¿Qué está previsto en la próxima actualización?\n" +
                                "\n" +
                                "- Notificación de un nivel de carga críticamente bajo\n" +
                                "- Notificación de un nivel de carga suficiente\n" +
                                "- Lista de tonos de llamada para elegir\n" +
                                "\n" +
                                "¡Gracias por usar la aplicación Charge Change Notifier!\n\n" +
                                "Política de privacidad\n" +
                                "\n" +
                                "1. El término \"información personal\" utilizado en este documento se define como cualquier información que identifique o pueda usarse para identificar, comunicarse o buscar a la persona a la que se refiere dicha información. La información personal que recopilamos estará sujeta a esta política de privacidad, con modificaciones ocasionales.\n" +
                                "\n" +
                                "2. No solicitamos Su dirección de correo electrónico, Teléfono ni ninguna información Sobre usted.\n" +
                                "\n" +
                                "3. No vendemos contenido.\n" +
                                "\n" +
                                "4. No requerimos registro.\n" +
                                "\n" +
                                "5. La seguridad de Su información personal no se verá comprometida.\n" +
                                "\n" +
                                "6. No solicitamos información de ubicación a través de nuestra aplicación móvil.\n" +
                                "\n" +
                                "7. No enviamos boletines por correo electrónico.\n" +
                                "\n" +
                                "Permisos requeridos\n" +
                                "\n" +
                                "1. El acceso a las notificaciones y la vibración se utiliza para notificar al usuario cuando se cambia el estado de carga del Teléfono.\n" +
                                "\n" +
                                "2. El acceso a Internet se utiliza para operar el Servicio AdMob.\n" +
                                "\n" +
                                "3. Evitar que el dispositivo entre en modo de suspensión es necesario para el correcto funcionamiento de la aplicación\n" +
                                "\n" +
                                "Contactos\n" +
                                "e-mail: gorbunovgroup@mail.ru");
                        break;
                    case "GE":
                        builder.setTitle("Über & Datenschutz");
                        builder.setMessage("Charge Change Notifier App hilft Ihnen, Ihr Gerät aufzuladen!\n" +
                                "\n" +
                                "Wenn sich Ihr Telefon oder Tablet plötzlich von der Steckdose löst oder das Kabel bricht und der Ladevorgang stoppt, erhalten Sie sofort eine Benachrichtigung darüber. Sie können die Benachrichtigung auch einschalten, wenn das Gerät aufgeladen wird!\n" +
                                "\n" +
                                "Aktuelle version - 1.0\n" +
                                "\n" +
                                "Was ist im nächsten Update geplant?\n" +
                                "\n" +
                                "- Benachrichtigung über einen kritisch niedrigen Ladezustand\n" +
                                "- Meldung eines ausreichenden Ladezustands\n" +
                                "- Liste der Klingeltöne zur Auswahl\n" +
                                "\n" +
                                "Vielen Dank für die Verwendung der Charge Ändern Notifier app!\n\n" +
                                "Datenschutz\n" +
                                "\n" +
                                "1. Der in diesem Dokument verwendete Begriff \"persönliche Informationen\" ist definiert als alle Informationen, die zur Identifizierung, Kommunikation oder Suche der Person, auf die sich solche Informationen beziehen, verwendet werden oder verwendet werden können. Die personenbezogenen Daten, die wir sammeln, unterliegen dieser Datenschutzrichtlinie und werden von Zeit zu Zeit geändert.\n" +
                                "\n" +
                                "2. Wir bitten nicht um Ihre E-Mail-Adresse, Telefonnummer oder Informationen über Sie.\n" +
                                "\n" +
                                "3. Wir verkaufen keine Inhalte.\n" +
                                "\n" +
                                "4. Wir benötigen keine Registrierung.\n" +
                                "\n" +
                                "5. Die Sicherheit Ihrer persönlichen Daten wird nicht beeinträchtigt.\n" +
                                "\n" +
                                "6. Wir bitten nicht um Standortinformationen über unsere mobile App.\n" +
                                "\n" +
                                "7. Wir senden keine E-Mail-Newsletter.\n" +
                                "\n" +
                                "Erforderliche Berechtigungen\n" +
                                "\n" +
                                "1. Der Zugriff auf Benachrichtigungen und Vibrationsalarm wird verwendet, um den Benutzer über die Änderung des Ladezustands des Telefons zu informieren.\n" +
                                "\n" +
                                "2. Der Zugriff auf das Internet wird für den AdMob-Dienst verwendet.\n" +
                                "\n" +
                                "3. Verhindern, dass das Gerät in den Ruhezustand ist notwendig, um die Anwendung korrekt zu arbeiten\n" +
                                "\n" +
                                "Kontakte\n" +
                                "e-mail: gorbunovgroup@mail.ru");
                        break;
                    case "FR":
                        builder.setTitle("À Propos Et Politique De Confidentialité");
                        builder.setMessage("L'application de sound_5 de changement de Charge vous aide à charger votre appareil!\n" +
                                "\n" +
                                "Si votre téléphone ou votre tablette se déconnecte soudainement de la prise murale ou si le câble se casse et que la charge s'arrête, vous recevrez immédiatement une sound_5 à ce sujet. Vous pouvez également activer la sound_5 lorsque l'appareil commence à charger!\n" +
                                "\n" +
                                "Version actuelle-1.0\n" +
                                "\n" +
                                "Ce qui est prévu dans la prochaine mise à jour?\n" +
                                "\n" +
                                "- Notification d'un niveau de charge extrêmement faible\n" +
                                "- Notification d'un niveau de charge suffisant\n" +
                                "- Liste des sonneries à choisir\n" +
                                "\n" +
                                "Merci d'utiliser L'application de sound_5 de changement de Charge!\n\n" +
                                "Politique de confidentialité\n" +
                                "\n" +
                                "1. Le terme “informations personnelles \" utilisé dans le présent document est défini comme toute information qui identifie ou peut être utilisée pour identifier, communiquer ou rechercher la personne à laquelle ces informations se rapportent. Les informations personnelles que nous collectons feront l'objet de la présente politique de confidentialité, telle que modifiée de temps à autre.\n" +
                                "\n" +
                                "2. Nous ne demandons pas votre adresse e-mail, votre téléphone ou toute autre information Vous concernant.\n" +
                                "\n" +
                                "3. Nous ne vendons pas de contenu.\n" +
                                "\n" +
                                "4. Nous ne demandons pas d'inscription.\n" +
                                "\n" +
                                "5. La sécurité de Vos informations personnelles ne sera pas compromise.\n" +
                                "\n" +
                                "6. Nous ne demandons pas d'informations de localisation via notre application mobile.\n" +
                                "\n" +
                                "7. Nous n'envoyons pas de newsletter par e-mail.\n" +
                                "\n" +
                                "Autorisations requises\n" +
                                "\n" +
                                "1. L'accès aux notifications et aux vibrations est utilisé pour informer l'utilisateur que l'état de charge du téléphone change.\n" +
                                "\n" +
                                "2. L'accès à Internet est utilisé pour le fonctionnement du service AdMob.\n" +
                                "\n" +
                                "3. Empêcher l'appareil de passer en mode veille est nécessaire pour le bon fonctionnement de l'application\n" +
                                "\n" +
                                "Relations\n" +
                                "e-mail: gorbunovgroup@mail.ru");
                        break;
                }
                builder.setPositiveButton("OK",
                        // устанавливаем слушатель
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // по нажатию создаем всплывающее окно с типом нажатой конпки
                            }
                        });
                builder.show();
            }
        });

       if(AppModel.IsActive){
           btnStartService.setEnabled(false);
           btnStopService.setEnabled(true);
           if(AppModel.OnStart)StartSwitch.setChecked(true);
           else StartSwitch.setChecked(false);
           if(AppModel.OnEnd)EndSwitch.setChecked(true);
           else EndSwitch.setChecked(false);
           StartSwitch.setEnabled(false);
           EndSwitch.setEnabled(false);
           btnStartService.setBackgroundResource(R.drawable.custom_button_disabled);
           btnStopService.setBackgroundResource(R.drawable.stop_button);
       }
       else{
           btnStartService.setEnabled(true);
           btnStopService.setEnabled(false);
           StartSwitch.setChecked(true);

           EndSwitch.setChecked(true);

           StartSwitch.setEnabled(true);
           EndSwitch.setEnabled(true);
           btnStartService.setBackgroundResource(R.drawable.custom_button);
           btnStopService.setBackgroundResource(R.drawable.stop_button_disabled);
       }





    }

    void UpdateLanguage(){
        String code = AppModel.CountryCode;
        TextView step1Header = findViewById(R.id.Step1Header);
        TextView step1Text = findViewById(R.id.Step1Text);
        TextView step2Header = findViewById(R.id.Step2Header);
        TextView step2Text = findViewById(R.id.Step2Text);
        TextView footerText = findViewById(R.id.FooterText);
        Switch startNotifText = findViewById(R.id.StartNotif);
        Switch endNotifText = findViewById(R.id.EndNotif);

        switch (code){
            case "EN":
                step1Header.setText("STEP 1");
                step2Header.setText("STEP 2");
                step1Text.setText("Set up your notifications");
                step2Text.setText("Start the service");
                footerText.setText("You can stop the service by clicking on the button");
                startNotifText.setText("Notification of the start of charging");
                endNotifText.setText("Notification about the end of charging");
                break;
            case "CH":
                step1Header.setText("步骤1");
                step2Header.setText("步骤2");
                step1Text.setText("自定义通知");
                step2Text.setText("启动应用程序");
                footerText.setText("您可以通过点击按钮停止该服务");
                startNotifText.setText("开始充电的通知");
                endNotifText.setText("关于充电结束的通知");
                break;
            case "RU":
                step1Header.setText("Шаг 1");
                step2Header.setText("Шаг 2");
                step1Text.setText("Настройте Ваши уведомления");
                step2Text.setText("Запустите приложение");
                footerText.setText("Вы можете остановить сервис, нажав на кнопку");
                startNotifText.setText("Уведомление о начале зарядки");
                endNotifText.setText("Уведомление об окончании зарядки");
                break;
            case "ES":
                step1Header.setText("MEDIDA 1");
                step2Header.setText("MEDIDA 2");
                step1Text.setText("Personaliza tus notificaciones");
                step2Text.setText("Inicie la aplicación");
                footerText.setText("Puede detener el Servicio haciendo clic en el botón");
                startNotifText.setText("Notificación de Inicio de carga");
                endNotifText.setText("Aviso de terminación de carga");
                break;
            case "GE":
                step1Header.setText("SCHRITT 1");
                step2Header.setText("SCHRITT 2");
                step1Text.setText("Passen Sie Ihre Benachrichtigungen");
                step2Text.setText("Starten Sie die Anwendung");
                footerText.setText("Sie können den Dienst stoppen, indem Sie auf die Schaltfläche klicken");
                startNotifText.setText("Benachrichtigung über den Beginn des Ladevorgangs");
                endNotifText.setText("Benachrichtigung über das Ende des Ladevorgangs");
                break;
            case "FR":
                step1Header.setText("ÉTAPE 1");
                step2Header.setText("ÉTAPE 2");
                step1Text.setText("Personnalisez vos notifications");
                step2Text.setText("Lancez l'application");
                footerText.setText("Vous pouvez arrêter le service en cliquant sur le bouton");
                startNotifText.setText("Notification de fin de charge");
                endNotifText.setText("Notification de fin de charge");
                break;
        }
    }





    public void startService() {
        btnStartService.setEnabled(false);
        btnStopService.setEnabled(true);
        AppModel.OnStart = StartSwitch.isChecked();
        AppModel.OnEnd = EndSwitch.isChecked();
        AppModel.IsActive=true;
        StartSwitch.setEnabled(false);
        EndSwitch.setEnabled(false);
        btnStartService.setBackgroundResource(R.drawable.custom_button_disabled);
        btnStopService.setBackgroundResource(R.drawable.stop_button);

        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this, serviceIntent);

        // объект Builder для создания диалогового окна
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // добавляем различные компоненты в диалоговое окно
        switch (AppModel.CountryCode){
            case "EN":
                builder.setTitle("Information");
                builder.setMessage("\nBefore charging, make sure that this sound_5 is still displayed at the top of the screen. \n\nIf it is not available, just restart the service by clicking on the Start button.\n\n");
                break;
            case "CH":
                builder.setTitle("信息");
                builder.setMessage("\n充电前，请确保此通知仍显示在屏幕顶部。\n\n如果它不可用，只需通过点击按钮重新启动该服务 Start\n\n");
                break;
            case "RU":
                builder.setTitle("Информация");
                builder.setMessage("\nПеред зарядкой телефона убедитесь, что уведомление на картинке все еще показывается среди уведомлений. \n\nЕсли такого уведомления нет, просто перезапустите сервис, нажав на кнопку Start.\n\n");
                break;
            case "ES":
                builder.setTitle("Información");
                builder.setMessage("\nAntes de cargar, asegúrese de que esta notificación todavía se muestra en la parte superior de la pantalla.\n\nSi no está disponible, simplemente reinicie el servicio haciendo clic en el botón Start.\n\n");
                break;
            case "GE":
                builder.setTitle("Information");
                builder.setMessage("\nStellen Sie vor dem Laden sicher, dass diese Benachrichtigung weiterhin oben auf dem Bildschirm angezeigt wird.\n\nWenn es nicht verfügbar ist, starten Sie den Dienst einfach neu, indem Sie auf die Schaltfläche Start klicken.\n\n");
                break;
            case "FR":
                builder.setTitle("Information");
                builder.setMessage("\nAvant de charger, assurez-vous que cette sound_5 est toujours affichée en haut de l'écran.\n\nS'il n'est pas disponible, redémarrez simplement le service en cliquant sur le bouton Start.\n\n");
                break;
        }


        // устанавливаем кнопку, которая отвечает за позитивный ответ
        ImageView v = new ImageView(this);
        v.setImageResource(R.drawable.app_working);
        builder.setView(v);

        builder.setPositiveButton("OK",
                // устанавливаем слушатель
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // по нажатию создаем всплывающее окно с типом нажатой конпки
                    }
                });
        // объект Builder создал диалоговое окно и оно готово появиться на экране
        // вызываем этот метод, чтобы показать AlertDialog на экране пользователя
        builder.show();
    }
    public void stopService() {
        AppModel.IsActive=false;
        btnStartService.setEnabled(true);
        btnStopService.setEnabled(false);
        StartSwitch.setEnabled(true);
        EndSwitch.setEnabled(true);
        btnStartService.setBackgroundResource(R.drawable.custom_button);
        btnStopService.setBackgroundResource(R.drawable.stop_button_disabled);

        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }
    public void sendNotify(){

        Date date = new Date();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Статус изменен")
                        .setContentText("Пора покормить кота " +formattedDate)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setSound(soundUri);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(101, builder.build());
    }

     void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }



    static void Notify(){
        boolean now = IsCharge();
        if(now!=status) {
            String title = "";
            switch (AppModel.CountryCode){
                case "EN":
                    title = "Status changed";
                    break;
                case "CH":
                    title = "状态已更改";
                    break;
                case "RU":
                    title = "Статус изменен";
                    break;
                case "ES":
                    title = "Estado cambiado";
                    break;
                case "GE":
                    title = "Status geändert";
                    break;
                case "FR":
                    title = "Statut modifié";
                    break;
            }
            status = now;
            if((AppModel.OnStart&&now)||(AppModel.OnEnd&&(!now))) {
                Uri soundUri;
                long[] pattern = { 0, 300, 400, 200 };
                Vibrator vibrator = (Vibrator) MainActivity.mContext.getSystemService(Context.VIBRATOR_SERVICE);

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(pattern,-1);
                }
                int icon;
                if(!now) {icon = R.drawable.battery_alert; soundUri = Uri.parse("android.resource://" +mContext.getPackageName() + "/" + R.raw.sound_5);}
                else {icon = R.drawable.battery_charging;soundUri = Uri.parse("android.resource://" +mContext.getPackageName() + "/" + R.raw.sound_4);}
                Date date = new Date();

                String strDateFormat = "hh:mm:ss a";
                DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
                String formattedDate = dateFormat.format(date);
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(mContext, CHANNEL_ID)
                                .setSmallIcon(icon)
                                .setContentTitle(title)
                                .setContentText(ShowDialog() + " " + formattedDate)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setSound(soundUri);

                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(mContext);
                notificationManager.notify(101, builder.build());
            }
        }

    }
    static boolean IsCharge(){
        final Intent batteryIntent = mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        return batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1) == BatteryManager.BATTERY_STATUS_CHARGING;
    }
    static String ShowDialog(){
        String result="";
        final Intent batteryIntent = mContext.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if(IsCharge()){
            switch (AppModel.CountryCode){
                case "EN":
                    result+="Charging, charge ";
                    break;
                case "CH":
                    result+="有收费，收费 ";
                    break;
                case "RU":
                    result+="Идет зарядка, заряд ";
                    break;
                case "ES":
                    result+="Carga, carga ";
                    break;
                case "GE":
                    result+="Ladevorgang, Ladung ";
                    break;
                case "FR":
                    result+="Charge en cours, charge ";
                    break;
            }
           }
        else {
            switch (AppModel.CountryCode){
                case "EN":
                    result+="Charging is interrupted, charge ";
                    break;
                case "CH":
                    result+="充电中断时，充电 ";
                    break;
                case "RU":
                    result+="Зарядка прервана, заряд ";
                    break;
                case "ES":
                    result+="Carga interrumpida, carga ";
                    break;
                case "GE":
                    result+="Laden unterbrochen, laden ";
                    break;
                case "FR":
                    result+="Charge interrompue, charge ";
                    break;
            }
            }

        //


        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float)scale;

        result+=(((int)(batteryPct*100)));
        result+="%";
        return result;
    }

    @Override
    protected void onResume(){
        super.onResume();
        mAdView.resume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mAdView.pause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mAdView.destroy();
    }




}

import { useThemeStore } from '../stores/themeStore';
import { Button } from '../components/ui/button';
import { Input } from '../components/ui/input';
import { Label } from '../components/ui/label';
import { Switch } from '../components/ui/switch';
import { Card, CardHeader, CardTitle, CardContent } from '../components/ui/card';
import { Separator } from '../components/ui/separator';

export default function SettingsPage() {
  const { theme, toggleTheme } = useThemeStore();

  return (
    <div>
      <h1 className="text-2xl font-bold mb-6">Settings</h1>

      <div className="space-y-6 max-w-2xl">
        <Card>
          <CardHeader>
            <CardTitle>Appearance</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="flex items-center justify-between">
              <Label htmlFor="theme-toggle">Theme</Label>
              <Button
                variant="outline"
                onClick={toggleTheme}
                id="theme-toggle"
              >
                {theme === 'light' ? 'Light' : 'Dark'}
              </Button>
            </div>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Notifications</CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div className="flex items-center justify-between">
              <Label htmlFor="email-notifications">Email notifications</Label>
              <Switch id="email-notifications" />
            </div>
            <Separator />
            <div className="flex items-center justify-between">
              <Label htmlFor="inapp-notifications">In-app notifications</Label>
              <Switch id="inapp-notifications" defaultChecked />
            </div>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Defaults</CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div className="space-y-2">
              <Label htmlFor="rate-limit">Default rate limit (ms)</Label>
              <Input id="rate-limit" type="number" defaultValue="2000" />
            </div>
            <div className="space-y-2">
              <Label htmlFor="timeout">Default timeout (ms)</Label>
              <Input id="timeout" type="number" defaultValue="30000" />
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
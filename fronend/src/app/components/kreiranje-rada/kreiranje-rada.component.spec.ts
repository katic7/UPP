import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { KreiranjeRadaComponent } from './kreiranje-rada.component';

describe('KreiranjeRadaComponent', () => {
  let component: KreiranjeRadaComponent;
  let fixture: ComponentFixture<KreiranjeRadaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ KreiranjeRadaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(KreiranjeRadaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
